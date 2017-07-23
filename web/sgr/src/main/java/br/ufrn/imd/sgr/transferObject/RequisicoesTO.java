package br.ufrn.imd.sgr.transferObject;

import java.io.Serializable;
import java.util.List;

import br.ufrn.sgr.model.Requisicao;


/**
 * Created by neto on 16/07/2017.
 */

public class RequisicoesTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Requisicao> listaRequisicoes;

    public List<Requisicao> getListaRequisicoes() {
        return listaRequisicoes;
    }

    public void setListaRequisicoes(List<Requisicao> listaRequisicoes) {
        this.listaRequisicoes = listaRequisicoes;
    }
}
