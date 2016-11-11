package com.imd030.sgr.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.imd030.sgr.R;
import com.imd030.sgr.entity.Anotacao;
import com.imd030.sgr.interfaces.AnotacaoListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by netou on 10/11/2016.
 */

public class AnotacaoListFragment extends ListFragment
        implements AdapterView.OnItemClickListener, View.OnClickListener {

    public static final String VIAGEM_SELECIONADA =
            "viagem_selecionada";

    private AnotacaoListener callback;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lista_anotacao,
                container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Button button =
                (Button) getActivity().findViewById(R.id.nova_anotacao);
        button.setOnClickListener(this);
        getListView().setOnItemClickListener(this);
        listarAnotacoesPorViagem(getArguments());
    }

    @Override
    public void onItemClick(AdapterView<?> parent,
                            View view, int position,
                            long id) {
        Anotacao anotacao = (Anotacao) getListAdapter().getItem(position);
        callback.anotacaoSelecionada(anotacao);
    }

    @Override
    public void onClick(View v) {
        callback.novaAnotacao();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (AnotacaoListener) activity;
    }

    private List<Anotacao> listarAnotacoes() {
        List<Anotacao> anotacoes = new ArrayList<Anotacao>();
        for (int i = 1; i <= 5; i++) {
            Anotacao anotacao = new Anotacao();
            anotacao.setDia(i);
            anotacao.setTitulo("Anotacao " + i);
            anotacao.setDescricao("Descrição " + i);
            anotacoes.add(anotacao);
        }
        return anotacoes;
    }

    public void listarAnotacoesPorViagem(Bundle bundle) {
        if(bundle != null &&
                bundle.containsKey(VIAGEM_SELECIONADA)){
//utilize a informação do bundle para buscar
// as anotacoes no banco de dados
            List<Anotacao> anotacoes = listarAnotacoes();
            ArrayAdapter<Anotacao> adapter =
                    new ArrayAdapter<Anotacao>(getActivity(),
                            android.R.layout.simple_list_item_1,
                            anotacoes);
            setListAdapter(adapter);
        }
    }
}
