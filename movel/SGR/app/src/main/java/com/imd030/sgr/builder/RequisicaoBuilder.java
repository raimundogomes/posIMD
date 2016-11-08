package com.imd030.sgr.builder;

import android.content.Context;
import android.util.Log;

import com.imd030.sgr.dao.RequisicaoDao;
import com.imd030.sgr.entity.Laboratorio;
import com.imd030.sgr.entity.Paciente;
import com.imd030.sgr.entity.Requisicao;
import com.imd030.sgr.entity.Solicitante;
import com.imd030.sgr.entity.StatusRequisicao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Neto on 29/05/2016.
 */
public class RequisicaoBuilder {

    private static int numeroGerador = 1;


    private RequisicaoDao requisicaoDao;

    public List<Requisicao> gerarRequisicoes(Context contexto) {
        List<Requisicao> requisicoes = new ArrayList<Requisicao>();
        //requisicoes.add(criarRequisicao("Solicitante 1", "Machado de Assis", "machadoassis@email.com"));
        requisicoes.add(criarRequisicao("Solicitante 1", "José de Alencar", "josealencar@email.com"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Lima Barreto", "limabarreto@email.com"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Raquel de Queiroz", "raquequeiroz@email.com"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Policarpo Quaresma", "policarpo@email.com"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Dom Casmurro", "domcasmurro@email.com"));
        requisicoes.add(criarRequisicao("Solicitante 1", "Pedro Alvez Cabral", "pedrocabral@email.com"));
        requisicoes.add(criarRequisicao("Solicitante 1", "João da Silva", "joaosilva@email.com"));

try {
    requisicaoDao = new RequisicaoDao(contexto);

    requisicaoDao.insert(criarRequisicao("Solicitante 1", "Machado de Assis", "machadoassis@email.com"));

    Log.d("Teste", requisicaoDao.listar().toString());
}catch (Exception e){
    e.printStackTrace();
    Log.d("Teste", e.getMessage());
}
        return requisicoes;
    }

    public Requisicao criarRequisicao(String nomeSolicitante, String nomePaciente, String emailPaciente){

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

        Paciente paciente = new Paciente( numeroGerador+13L, nomePaciente, "21/06/1838", emailPaciente, "00079265403", "000023838");

        requisicao.setPaciente(paciente);

        Laboratorio laboratorio = Laboratorio.MICROBIOLOGIA;

        requisicao.setLaboratorio(laboratorio);

        Solicitante solicitante = new Solicitante();
        solicitante.setNome(nomeSolicitante);
        requisicao.setSolicitante(solicitante);

        return requisicao;
    }
}
