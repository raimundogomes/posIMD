package br.ufrn.sgr.dao.impl;

import br.ufrn.sgr.builder.RequisicaoBuilder;
import br.ufrn.sgr.dao.RequisicaoDao;
import br.ufrn.sgr.model.Requisicao;
import br.ufrn.sgr.model.StatusRequisicao;

public class RequisicaoDaoImpl implements RequisicaoDao {

	@Override
	public void inserir(Requisicao requisicao) {
		
		RequisicaoBuilder.getRequisicoes().put(RequisicaoBuilder.getNumeroGerador(), requisicao);

	}

	@Override
	public void atualizar(Requisicao requisicao) {
		RequisicaoBuilder.getRequisicoes().put(requisicao.getNumero(), requisicao);

	}

	@Override
	public Requisicao cancelar(long numeroRequisicao) {
		Requisicao requisicao = RequisicaoBuilder.getRequisicao(numeroRequisicao);
		requisicao.setStatus(StatusRequisicao.CANCELADA);
		return requisicao;
	}

}
