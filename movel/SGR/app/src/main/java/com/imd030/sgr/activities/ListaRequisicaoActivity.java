package com.imd030.sgr.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
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
import com.google.gson.JsonIOException;
import com.imd030.sgr.R;
import com.imd030.sgr.adapter.RequisicaoAdapter;
import com.imd030.sgr.comparator.RequisicaoComparator;
import com.imd030.sgr.dao.RequisicaoDao;
import com.imd030.sgr.entity.Email;
import com.imd030.sgr.entity.Paciente;
import com.imd030.sgr.entity.Requisicao;
import com.imd030.sgr.entity.StatusRequisicao;
import com.imd030.sgr.utils.Constantes;
import com.imd030.sgr.utils.DateUtils;
import com.imd030.sgr.utils.DetectaConexao;
import com.imd030.sgr.utils.EmailUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ListaRequisicaoActivity extends PrincipalActivity implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener,
        DialogInterface.OnClickListener,
        TextWatcher {

    public static final String SUBJECT_EMAIL = "[SGR] - Encaminhamento de Requisição";

    private RequestQueue queue;

    private List<Requisicao> requisicoes = new ArrayList<Requisicao>();


    private List<Requisicao> requisicoesfiltradas;

    private Requisicao requisicaoSelecionada = null;

    private RequisicaoAdapter requisicaoAdapter;

    private int criterioOrdenacaoSelecionado = Constantes.CRITERIO_DATA_REQUISICAO;

    private RequisicaoDao requisicaoDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_requisicoes);

        requisicaoDao = new RequisicaoDao(this);

        if(requisicoes.size()==0){
            requisicoes = requisicaoDao.listar();
        }

        queue = Volley.newRequestQueue(ListaRequisicaoActivity.this);

        ListView listView = (ListView) findViewById(R.id.list_requisicao);

        registerForContextMenu(listView);

        requisicoesfiltradas = ((List) ((ArrayList) requisicoes).clone());

        ordenarComBaseConfiguracao();

        requisicaoAdapter = new RequisicaoAdapter(this,  requisicoesfiltradas);

        listView.setAdapter(requisicaoAdapter);

        listView.setOnItemClickListener(this);

        listView.setOnItemLongClickListener(this);

        //edit search
        EditText editSearch = (EditText) findViewById(R.id.edit_search);

        editSearch.addTextChangedListener(this);


    }

    private void ordenarComBaseConfiguracao() {
        SharedPreferences preferencias = getSharedPreferences(Constantes.PREF_NAME, MODE_PRIVATE);

        int configuracaoOrdenacao = preferencias.getInt(Constantes.CONFIGURACAO_CRITERIO_SELECIONADO, criterioOrdenacaoSelecionado);

        Collections.sort(requisicoesfiltradas, new RequisicaoComparator(configuracaoOrdenacao));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.menu_sair:
                finish();
                break;
            case R.id.menu_sincronizar:
                sicronizarRequisicoes(requisicoes);
                break;
            case R.id.menu_configuracoes:
                abrirConfiguracoes();
                break;
            case R.id.menu_desconectar:
                desconectar();
                break;
            case R.id.menu_nova_requisicao:
                novaRequisicao();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void desconectar() {
        SharedPreferences sp = getSharedPreferences(Constantes.PREF_NAME, MODE_PRIVATE);

        boolean b = sp.getBoolean(Constantes.CONFIGURACAO_CONECTADO, false);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();

        finish();
    }

    private void abrirConfiguracoes() {
        Intent intent = new Intent(this, ConfiguracoesActivity.class);
        intent.putExtra(Constantes.CONFIGURACAO_ACTIVITY, criterioOrdenacaoSelecionado);
        startActivityForResult(intent, Constantes.INDICE_ACTIVITY_CONFIGURACOES);
    }

    public void novaRequisicao() {

        DetectaConexao detectaConexao = new DetectaConexao(getApplicationContext());
        if(detectaConexao.existeConexao()){
            Intent intent = new Intent(this, PesquisarPacienteActivity.class);
            startActivityForResult(intent, Constantes.INDICE_ACTIVITY_NOVA_REQUISICAO);
        }
        else{
            Toast toast = Toast.makeText(this, DetectaConexao.FALHA_CONEXAO,
                    Toast.LENGTH_LONG);
            toast.show();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case Constantes.INDICE_ACTIVITY_CONFIGURACOES:
                if(resultCode == RESULT_OK){
                    criterioOrdenacaoSelecionado = (int) data.getSerializableExtra(Constantes.CONFIGURACAO_ACTIVITY);
                    Collections.sort(requisicoesfiltradas, new RequisicaoComparator(criterioOrdenacaoSelecionado));

                    salvarConfiguracaoOrdenacao();

                    requisicaoAdapter.notifyDataSetChanged();
                }
                break;
            case Constantes.INDICE_ACTIVITY_NOVA_REQUISICAO:
                if(resultCode == RESULT_OK){
                    Requisicao novaRequisicao = (Requisicao) data.getSerializableExtra(Constantes.REQUISICAO_NOVA_ACTIVITY);
                    requisicoes.add(novaRequisicao);
                    requisicoesfiltradas.add(novaRequisicao);
                    Collections.sort(requisicoes, new RequisicaoComparator(criterioOrdenacaoSelecionado));
                    Collections.sort(requisicoesfiltradas, new RequisicaoComparator(criterioOrdenacaoSelecionado));
                    requisicaoAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Nova requisição inserida. Nº: " + novaRequisicao.getNumeroFormatado() , Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void salvarConfiguracaoOrdenacao() {
        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putInt (Constantes.CONFIGURACAO_CRITERIO_SELECIONADO, criterioOrdenacaoSelecionado);
        editor.commit();
    }

    private void sicronizarRequisicoes(List<Requisicao> requisicoes) {

        DetectaConexao detectaConexao = new DetectaConexao(getApplicationContext());
        if(detectaConexao.existeConexao()){

            exibirMensagemSicronizacao();
        }
        else{
            Toast toast = Toast.makeText(this, DetectaConexao.FALHA_CONEXAO,
                    Toast.LENGTH_LONG);
            toast.show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view,
                            int position, long id) {

        Requisicao requisicaSelecionada = requisicoes.get(position);

        Intent acao = new Intent(ListaRequisicaoActivity.this, DetalheRequisicaoActivity.class);

        acao.putExtra(Constantes.REQUISICAO_DETALHE_ACTIVITY, requisicaSelecionada);

        startActivity(acao);

    }

    public void exibirMensagemSicronizacao() {

        final ProgressDialog dialog = new ProgressDialog(ListaRequisicaoActivity.this);
        dialog.setTitle("Sincronizando as requisições...");
        dialog.setMessage("Aguarde, por favor.");
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.show();

        long delayInMillis = 2000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, delayInMillis);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        requisicaoSelecionada = (Requisicao) parent.getItemAtPosition(position);
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contexto_requisicao, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cancelar_requisicao:
                cancelarRequisicao();
                break;
            case R.id.menu_encaminhar_requisicao:
                encaminharRequisicao();
                break;

            case R.id.menu_dados_paciente:
                visualizarDadosPaciente();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void visualizarDadosPaciente() {
        Intent acao = new Intent(ListaRequisicaoActivity.this, PacienteActivity.class);

        acao.putExtra(Constantes.DADOS_PACIENTE_ACTIVITY, requisicaoSelecionada.getPaciente());

        startActivity(acao);

    }

    private void encaminharRequisicao() {
        if(requisicaoSelecionada.getPaciente().getEmail()!=null){
            Email email = new Email(new  String[]{requisicaoSelecionada.getPaciente().getEmail()}, SUBJECT_EMAIL, montarCorpoEmail());
            EmailUtil emailUtil = new EmailUtil();
            Intent intentEmail =  emailUtil.enviarEmail(email);

            try{
                startActivity(intentEmail);
            } catch (android.content.ActivityNotFoundException ex) {

                Toast.makeText(this,emailUtil.getMensagemFalha() , Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Paciente não possui e-mail." , Toast.LENGTH_SHORT).show();
        }
    }

    private String montarCorpoEmail() {
        String corpoEmail = new String();
        corpoEmail = "Prezado(a) " + requisicaoSelecionada.getPaciente().getNome() + " segue as informações da sua requisição:" + "\n" +
                "Número da requisição: " + requisicaoSelecionada.getNumeroFormatado() + "\n" +
                "Data da requisição: " + DateUtils.obterDataPorExtenso(requisicaoSelecionada.getDataRequisicao()) + "\n" +
                "Status da requisição: " + requisicaoSelecionada.getStatus().getDescricao() + "\n" +
                "Exames: " + requisicaoSelecionada.getExamesFormatados();
        return corpoEmail;
    }

    private void cancelarRequisicao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Confirma o cancelamento da requisção de número " + requisicaoSelecionada.getNumeroFormatado() + "?");
        builder.setPositiveButton("Sim", this);
        builder.setNegativeButton("Não", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {


        //realizar o cancelamento da requisição pelo serviço
        cancelarRequisicaoServico(requisicaoSelecionada.getNumero());

    }

    private void cancelarRequisicaoServico(long numeroRequisicao) {

        String url = Constantes.URL_REQUISICAO + "cancelarRequisicao";

        final JSONObject jsonBody;
        try {
            jsonBody = new JSONObject("{\"numeroRequisicao\":\"" + numeroRequisicao +  "\"}");

            Log.d("Teste", jsonBody.get("numeroRequisicao").toString());

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response
                .Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Teste", response.toString());
                requisicaoSelecionada.setStatus(StatusRequisicao.CANCELADA);
                requisicaoAdapter.notifyDataSetChanged();
                requisicaoSelecionada = null;
                Toast.makeText(getApplicationContext(),
                        "Requisição cancelada com sucesso.",
                        Toast.LENGTH_SHORT).show();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Teste", error.toString());
                Toast.makeText(getApplicationContext(),
                        "Não foi possível estabelecer conexão para cancelar a requisição.",
                        Toast.LENGTH_LONG).show();
            }
        });

        queue.add(jsObjRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //TODO: pesquisar forma mais eficiente de realizar a consulta - ver Filterable

        //texto digitado
        String nomePaciente = s.toString().toLowerCase();

        //limpa a lista
        requisicoesfiltradas.clear();

        //atualiza a lista
        for(Requisicao requisicao: requisicoes){
            if(requisicao.getPaciente().getNome().toLowerCase().contains(nomePaciente)){
                requisicoesfiltradas.add(requisicao);
            }
        }

        requisicaoAdapter.notifyDataSetChanged();
    }

    @Override
    public void afterTextChanged(Editable s) {}


}