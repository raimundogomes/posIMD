package br.ufrn.sgr.dao;

import java.util.List;

import br.ufrn.sgr.model.Requisicao;

public interface RequisicaoDao {

	Requisicao inserir(Requisicao requisicao);

	void atualizar(Requisicao requisicao);

	Requisicao cancelar(long numeroRequisicao) throws Exception;

	List<Requisicao> listarRequisicoes();

	Requisicao pesquisarPorNumero(long numero) throws Exception;

}
