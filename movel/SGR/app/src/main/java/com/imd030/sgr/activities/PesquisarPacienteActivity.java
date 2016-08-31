package com.imd030.sgr.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.imd030.sgr.comparator.RequisicaoComparator;
import com.imd030.sgr.entity.Paciente;
import com.imd030.sgr.entity.Requisicao;
import com.imd030.sgr.utils.Constantes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PesquisarPacienteActivity extends PrincipalActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private RequestQueue queue;

    final String url = "http://192.168.25.30:8080/sgr/service/paciente/";

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_paciente);

        Button button = (Button) findViewById(R.id.botao_pesquisar_paciente);

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

        if(camposEstaoPreenchidos()) {
            String prontuario =  ((TextView)findViewById(R.id.text_prontuario)).getText().toString();
            String cpf =  ((TextView)findViewById(R.id.text_cpf)).getText().toString();

            if(!"".equals(prontuario) || !"".equals(cpf)){
                Log.d("Teste", "pesquisar por prontuário");
                pesquisarPaciente(prontuario, cpf);
            }
            else{

                pesquisarPacientesPeloNome(view);
            }

        }
    }

    private void pesquisarPaciente(String prontuario, String cpf) {
        String urlPaciente = url;

        if(!"".equals(prontuario)){
            urlPaciente += "prontuario/"+prontuario;
        }else{
            urlPaciente += "cpf/"+cpf;
        }

        Log.d("Teste", urlPaciente);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, urlPaciente, null, new Response
                .Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Teste", response.toString());
                Gson gson = new Gson();

                    Paciente paciente = gson.fromJson(response.toString(), Paciente.class);
                    Intent i = new Intent(PesquisarPacienteActivity.this, NovaRequisicaoActivity.class);
                    startActivity(i);

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(jsObjRequest);
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
                            Paciente paciente = gson.fromJson(response.getJSONObject(i).toString(), Paciente.class);
                            Log.i("Teste", paciente.getNome());
                            list.add(paciente);
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
        Paciente paciente = (Paciente) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, NovaRequisicaoActivity.class);
        intent.putExtra(Constantes.DADOS_PACIENTE_REQUISICAO_NOVA_ACTIVITY, paciente);
        startActivityForResult(intent, Constantes.INDICE_ACTIVITY_NOVA_REQUISICAO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case Constantes.INDICE_ACTIVITY_NOVA_REQUISICAO:
                if(resultCode == RESULT_OK){
                    Requisicao novaRequisicao = (Requisicao) data.getSerializableExtra(Constantes.REQUISICAO_NOVA_ACTIVITY);
                    Intent result = new Intent();
                    result.putExtra(Constantes.REQUISICAO_NOVA_ACTIVITY, novaRequisicao);
                    setResult(RESULT_OK, result);
                    finish();
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
