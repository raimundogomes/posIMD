package com.imd030.sgr.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.imd030.sgr.R;
import com.imd030.sgr.adapter.PacienteAdapter;
import com.imd030.sgr.entity.Paciente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PesquisarPacienteActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private RequestQueue queue;

    final String url = "http://192.168.0.27/sgr/service/paciente/";

    private EditText prontuario;

    private EditText cpf;

    private EditText nomePaciente;

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_paciente);

        Button button = (Button) findViewById(R.id.botao_pesquisar_paciente);

        nomePaciente = (EditText) findViewById(R.id.text_nome_paciente);

        queue = Volley.newRequestQueue(this);

        listview = (ListView) findViewById(R.id.listView_paciente);

        button.setOnClickListener(this);

        listview.setOnItemClickListener(this);

    }

    public boolean camposEstaoPreenchidos(){
        String prontuario =  ((TextView)findViewById(R.id.text_prontuario)).getText().toString();
        String cpf =  ((TextView)findViewById(R.id.text_cpf)).getText().toString();
        String nome = ((TextView) findViewById(R.id.text_nome_paciente)).getText().toString();

        if( !"".equals(prontuario) || !"".equals(cpf) || !"".equals(nome) ){

            return true;

        }
        else{
            Toast toast = Toast.makeText(this, "Preencha o prontuário, CPF ou nome do paciente!", Toast.LENGTH_SHORT);
            toast.show();

            return false;
        }

    }

    @Override
    public void onClick(final View view) {
        //enviando informação
        Log.d("Teste", nomePaciente.getText().toString());

        if(camposEstaoPreenchidos()) {
            String prontuario =  ((TextView)findViewById(R.id.text_prontuario)).getText().toString();
            String cpf =  ((TextView)findViewById(R.id.text_cpf)).getText().toString();

            if(!"".equals(prontuario) || !"".equals(cpf)){
                Log.d("Teste", nomePaciente.getText().toString());
            }
            else{
           
                pesquisarPacientesPeloNome(view);
            }

        }
    }

    private void pesquisarPacientesPeloNome(final View view) {
        try {
            String nome = ((TextView) findViewById(R.id.text_nome_paciente)).getText().toString();
            String urlNome = url + "nome/" + nome;
            JsonArrayRequest jsObjRequest = new JsonArrayRequest(Request.Method.GET, urlNome, null, new Response
                    .Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    try {
                        Gson gson = new Gson();
                        Log.i("Teste", "teste 1");
                        final List<Paciente> list = new ArrayList<Paciente>();
                        for (int i = 0; i < response.length(); ++i) {
                            Paciente ubs = gson.fromJson(response.getJSONObject(i).toString(), Paciente.class);
                            Log.i("Teste", ubs.getNome());
                            list.add(ubs);
                        }
                        final PacienteAdapter adapter = new PacienteAdapter(view.getContext(), list);
                        listview.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getApplicationContext(),
                            "ERRO ", Toast.LENGTH_LONG)
                            .show();
                }
            });

            jsObjRequest.setTag("REST");

            queue.add(jsObjRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

        final String item = (String) parent.getItemAtPosition(position);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET,
                String.format(String.format(url, nomePaciente.getText().toString())+"/"+position, nomePaciente.getText().toString()), null, new Response
                .Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {

                String strName = null;
                try {
                    Intent i = new Intent(PesquisarPacienteActivity.this, NovaRequisicaoActivity.class);
                    i.putExtra("lat", response.getDouble("latitude"));
                    i.putExtra("long", response.getDouble("longitude"));
                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(jsObjRequest);

    }
}
