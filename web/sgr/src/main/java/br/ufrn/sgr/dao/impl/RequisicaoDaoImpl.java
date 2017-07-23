package br.ufrn.sgr.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufrn.sgr.builder.ExamesBulder;
import br.ufrn.sgr.builder.RequisicaoBuilder;
import br.ufrn.sgr.dao.RequisicaoDao;
import br.ufrn.sgr.model.Exame;
import br.ufrn.sgr.model.Requisicao;
import br.ufrn.sgr.model.ResultadoExame;
import br.ufrn.sgr.model.SituacaoExame;
import br.ufrn.sgr.model.SituacaoRequisicao;

public class RequisicaoDaoImpl implements RequisicaoDao {

	@Override
	public Requisicao inserir(Requisicao requisicao) {
		requisicao.setNumero(RequisicaoBuilder.getNumeroGerador());
		requisicao.setDataUltimaModificacao(new Date());
		RequisicaoBuilder.getRequisicoes().put(RequisicaoBuilder.getNumeroGerador(), requisicao);
		RequisicaoBuilder.incrementarNumeroGerador();
		System.out.println(RequisicaoBuilder.getRequisicoes());
		
		for(Exame e : requisicao.getExames()){
			e.setId(ExamesBulder.getNumeroGerador());
			e.setNumero(ExamesBulder.getNumeroGerador());
			ExamesBulder.incrementarNumeroGerador();
		}
		
		return requisicao;

	}

	@Override
	public void atualizar(Requisicao requisicao) {
		RequisicaoBuilder.getRequisicoes().put(requisicao.getNumero(), requisicao);

	}

	@Override
	public Requisicao cancelar(long numeroRequisicao) throws Exception {
		Requisicao requisicao = RequisicaoBuilder.getRequisicao(numeroRequisicao);
		requisicao.setStatus(SituacaoRequisicao.CANCELADA);
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

	@Override
	public Requisicao rejeitar(long numero) throws Exception {
		Requisicao requisicao = RequisicaoBuilder.getRequisicao(numero);
		requisicao.setStatus(SituacaoRequisicao.REJEITADA);
		
		for (Exame e :requisicao.getExames()) {
			e.setSituacaoExame(SituacaoExame.REJEITADO);
			e.setResultadoExame(ResultadoExame.ANALISE_NAO_REALIZADA);
			e.setResultadoCompleto("teste");
		}
		
		return requisicao;
	}

}
