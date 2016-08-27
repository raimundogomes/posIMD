package com.imd030.sgr.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.imd030.sgr.comparator.Paciente;
import com.imd030.sgr.utils.NetworkQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PesquisarPacienteActivity extends Activity  implements
        View.OnClickListener{

    private ProgressDialog load;

    final String url = "http://192.168.0.27/sgr/service/paciente/nome";

    private RequestQueue queue;

    private ListView listView;

    private PacienteAdapter pacienteAdapter;

    private List<Paciente> listaPacientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_paciente);

        queue = Volley.newRequestQueue(this);

        listView  = (ListView) findViewById(R.id.list_pacientes);

        listaPacientes = new ArrayList<Paciente>();

        pacienteAdapter = new PacienteAdapter(getApplicationContext(),  listaPacientes);

        listView.setAdapter(pacienteAdapter);

        Button pesquisar = (Button)findViewById(R.id.botao_pesquisar_paciente);

        pesquisar.setOnClickListener(this);


    }

    public void pesquisar(View v){
        String prontuario =  ((TextView)findViewById(R.id.text_prontuario)).getText().toString();
        String cpf =  ((TextView)findViewById(R.id.text_cpf)).getText().toString();
        String nome = ((TextView) findViewById(R.id.text_nome_paciente)).getText().toString();

        if( !"".equals(prontuario) || !"".equals(cpf) || !"".equals(nome) ){

            try {

                JsonArrayRequest jsObjRequest = new JsonArrayRequest(Request.Method.GET,
                        url, null, new Response
                        .Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Gson gson = new Gson();
                            Log.i("Teste","teste 1");

                            for (int i = 0; i < response.length(); ++i) {
                                Paciente paciente = gson.fromJson(response.getJSONObject(i).toString(), Paciente.class);
                                Log.i("Teste",paciente.getNome());
                                listaPacientes.add(paciente);
                            }

                            pacienteAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Tes53", error.toString());
                        Toast.makeText(getApplicationContext(),
                                "ERRO "+ error.toString(), Toast.LENGTH_LONG)
                                .show();
                    }
                });

                jsObjRequest.setTag("REST");

                queue.add(jsObjRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else{
            Toast toast = Toast.makeText(this, "Preencha o prontuário, CPF ou nome do paciente!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }


    @Override
    public void onClick(View v) {
        String prontuario =  ((TextView)findViewById(R.id.text_prontuario)).getText().toString();
        String cpf =  ((TextView)findViewById(R.id.text_cpf)).getText().toString();
        String nome = ((TextView) findViewById(R.id.text_nome_paciente)).getText().toString();

        if( !"".equals(prontuario) || !"".equals(cpf) || !"".equals(nome) ){

            try {

                JsonArrayRequest jsObjRequest = new JsonArrayRequest(Request.Method.GET,
                        url, null, new Response
                        .Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Gson gson = new Gson();
                            Log.i("Teste","teste 1");

                            for (int i = 0; i < response.length(); ++i) {
                                Paciente paciente = gson.fromJson(response.getJSONObject(i).toString(), Paciente.class);
                                Log.i("Teste",paciente.getNome());
                                listaPacientes.add(paciente);
                            }
                            pacienteAdapter = new PacienteAdapter(getApplicationContext(),  listaPacientes);

                            listView.setAdapter(pacienteAdapter);
                            pacienteAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Teste", error.toString());
                        Toast.makeText(getApplicationContext(),
                                "ERRO "+ error.toString(), Toast.LENGTH_LONG)
                                .show();
                    }
                });

                jsObjRequest.setTag("REST");

                queue.add(jsObjRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else{
            Toast toast = Toast.makeText(this, "Preencha o prontuário, CPF ou nome do paciente!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
