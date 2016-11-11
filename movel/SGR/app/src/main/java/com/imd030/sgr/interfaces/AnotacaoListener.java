package com.imd030.sgr.interfaces;

import android.os.Bundle;

import com.imd030.sgr.entity.Anotacao;

/**
 * Created by netou on 10/11/2016.
 */

public interface AnotacaoListener {
    void viagemSelecionada(Bundle bundle);
    void anotacaoSelecionada(Anotacao anotacao);
    void novaAnotacao();
}
