package com.imd030.sgr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.imd030.sgr.R;
import com.imd030.sgr.entity.Anotacao;

/**
 * Created by netou on 10/11/2016.
 */

public class AnotacaoFragment extends Fragment
        implements View.OnClickListener {
    private EditText dia, titulo, descricao;
    private Button botaoSalvar;
    private Anotacao anotacao;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.anotacao, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        dia = (EditText) getActivity().findViewById(R.id.dia);
        titulo = (EditText) getActivity().findViewById(R.id.titulo);

        descricao = (EditText) getActivity().findViewById(R.id.descricao);
        botaoSalvar = (Button) getActivity().findViewById(R.id.salvar);
        botaoSalvar.setOnClickListener(this);
        if(anotacao != null) {
            prepararEdicao(anotacao);
        }
    }
    public void setAnotacao(Anotacao anotacao) {
        this.anotacao = anotacao;
    }
    public void prepararEdicao(Anotacao anotacao) {
        setAnotacao(anotacao);
        dia.setText(anotacao.getDia().toString());
        titulo.setText(anotacao.getTitulo());
        descricao.setText(anotacao.getDescricao());
    }

    public void criarNovaAnotacao() {
        anotacao = new Anotacao();
        dia.setText("");
        titulo.setText("");
        descricao.setText("");
    }

    @Override
    public void onClick(View v) {
// salvar Anotacao no banco de dados
    }
}