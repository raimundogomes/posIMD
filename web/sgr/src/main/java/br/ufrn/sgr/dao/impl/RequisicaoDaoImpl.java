package br.ufrn.sgr.dao.impl;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.sgr.builder.RequisicaoBuilder;
import br.ufrn.sgr.dao.RequisicaoDao;
import br.ufrn.sgr.model.Requisicao;
import br.ufrn.sgr.model.StatusRequisicao;

public class RequisicaoDaoImpl implements RequisicaoDao {

	@Override
	public Requisicao inserir(Requisicao requisicao) {
		requisicao.setNumero(RequisicaoBuilder.getNumeroGerador());
		RequisicaoBuilder.getRequisicoes().put(RequisicaoBuilder.getNumeroGerador(), requisicao);
		RequisicaoBuilder.incrementarNumeroGerador();
		
		return requisicao;

	}

	@Override
	public void atualizar(Requisicao requisicao) {
		RequisicaoBuilder.getRequisicoes().put(requisicao.getNumero(), requisicao);

	}

	@Override
	public Requisicao cancelar(long numeroRequisicao) throws Exception {
		Requisicao requisicao = RequisicaoBuilder.getRequisicao(numeroRequisicao);
		requisicao.setStatus(StatusRequisicao.CANCELADA);
		return requisicao;
	}

	@Override
	public List<Requisicao> listarRequisicoes() {
		return new ArrayList<Requisicao>(RequisicaoBuilder.getRequisicoes().values());
	}

	@Override
	public Requisicao pesquisarPorNumero(long numero) throws Exception {
		
		return RequisicaoBuilder.getRequisicao(numero);
	}

}
