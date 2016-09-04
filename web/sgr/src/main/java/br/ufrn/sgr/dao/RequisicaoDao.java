package br.ufrn.sgr.dao;

import br.ufrn.sgr.model.Requisicao;

public interface RequisicaoDao {

	void inserir(Requisicao requisicao);

	void atualizar(Requisicao requisicao);

	Requisicao cancelar(long numeroRequisicao);

}
