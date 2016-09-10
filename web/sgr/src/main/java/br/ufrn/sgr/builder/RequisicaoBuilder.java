package br.ufrn.sgr.builder;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.ufrn.sgr.exception.PacienteNaoEncontradoException;
import br.ufrn.sgr.factory.PacienteFactory;
import br.ufrn.sgr.model.Laboratorio;
import br.ufrn.sgr.model.Requisicao;
import br.ufrn.sgr.model.Solicitante;
import br.ufrn.sgr.model.StatusRequisicao;


public class RequisicaoBuilder {

    public static long getNumeroGerador() {
    	numeroGerador++;
		return numeroGerador;
	}


	private static long numeroGerador = 1;
    
    private static Map<Long,Requisicao> requisicoes = new HashMap<Long,Requisicao>();
    
    static{
    	requisicoes.put(numeroGerador, criarRequisicao());
        requisicoes.put(numeroGerador, criarRequisicao());
        requisicoes.put(numeroGerador, criarRequisicao());
        requisicoes.put(numeroGerador, criarRequisicao());
        requisicoes.put(numeroGerador, criarRequisicao());
        requisicoes.put(numeroGerador, criarRequisicao());
        requisicoes.put(numeroGerador, criarRequisicao());
        requisicoes.put(numeroGerador, criarRequisicao());
    }

    public static Map<Long, Requisicao> getRequisicoes() {
		return requisicoes;
	}


	public static Requisicao criarRequisicao(){

        ExamesBulder examesBulder = new ExamesBulder();

        Requisicao requisicao = new Requisicao();

        requisicao.setDataRequisicao(new Date());

        requisicao.setNumero(numeroGerador++);

        requisicao.setExames(examesBulder.getListaExames());

        if(requisicao.getNumero()%2==0) {
            requisicao.setStatus(StatusRequisicao.SOLICITADA);

            requisicao.getExames().add(examesBulder.adicionaExameSangue());
            requisicao.getExames().add(examesBulder.adicionaExameUrina());
        }
        else{
            requisicao.setStatus(StatusRequisicao.LAUDO_DEFINITIVO);
            requisicao.getExames().add(examesBulder.adicionaExameSangueResultadoDefinitivo());
            requisicao.setDataFim(new Date());

        }

        try {
			requisicao.setPaciente(PacienteFactory.getByNumeroProntuario(1000));
		} catch (PacienteNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Laboratorio laboratorio = new Laboratorio("Microbiologia", "84987654322");

        requisicao.setLaboratorio(laboratorio);

        Solicitante solicitante = new Solicitante();
        solicitante.setNome("Solicitante 1");
        requisicao.setSolicitante(solicitante);

        return requisicao;
    }




	public static Requisicao getRequisicao(long numeroRequisicao) throws Exception {
		if(requisicoes.containsKey(numeroRequisicao)){
			return requisicoes.get(numeroRequisicao);
		}
		
		throw new Exception("Requisição não encontrada");
	}
}
