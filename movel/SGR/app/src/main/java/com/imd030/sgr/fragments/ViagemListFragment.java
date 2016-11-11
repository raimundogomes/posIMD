package com.imd030.sgr.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.imd030.sgr.interfaces.AnotacaoListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by netou on 10/11/2016.
 */
public class ViagemListFragment extends ListFragment
        implements AdapterView.OnItemClickListener {

    private AnotacaoListener callback;

    public static final String VIAGEM_SELECIONADA =
            "viagem_selecionada";

    @Override
    public void onStart() {
        super.onStart();
        List<String> viagens = Arrays.asList("Campo Grande", "São Paulo",
                "Miami");
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, viagens);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent,
                            View view, int position,
                            long id) {
        String viagem = (String) getListAdapter().getItem(position);
        Bundle bundle = new Bundle();
        bundle.putString(VIAGEM_SELECIONADA, viagem);

        callback.viagemSelecionada(bundle);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (AnotacaoListener) activity;
    }
}
