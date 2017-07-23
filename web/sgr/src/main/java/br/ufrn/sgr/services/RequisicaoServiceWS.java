package br.ufrn.sgr.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.ufrn.imd.sgr.transferObject.RequisicoesTO;
import br.ufrn.sgr.dao.RequisicaoDao;
import br.ufrn.sgr.dao.impl.RequisicaoDaoImpl;
import br.ufrn.sgr.model.Requisicao;
import br.ufrn.sgr.model.SituacaoRequisicao;

@Path("requisicao") 
public class RequisicaoServiceWS {

	private RequisicaoDao requisicaoDao;
	
	@POST
	@Path("/inserirRequisicao")
	@Produces("application/json")
	public Requisicao inserir(Requisicao requisicao) {
		
		return requisicaoDao.inserir(requisicao);

	}
	
	@POST
	@Path("/atualizarRequisicao")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Requisicao requisicao) {
		
		requisicaoDao.atualizar(requisicao);

		return Response.status(201).entity(requisicao.toString()).build();
		
	}
	
	@POST
	@Path("/cancelarRequisicao")
	@Produces("application/json")
	public Requisicao cancelar(RequisicaoIdTO requisicaoIdTO) throws Exception{
		return requisicaoDao.cancelar(requisicaoIdTO.getNumeroRequisicao());
	}
	

	@GET
	@Path("/pesquisarRequisicaoPaginada")
	@Produces("text/html; charset=UTF-8")
	public String pesquisarRequisicao(@DefaultValue("0") @QueryParam("inicio") int inicio,
			@DefaultValue("10") @QueryParam("limite") int limite)	{
		int menorLimite = (requisicaoDao.listarRequisicoes().size()>limite)?limite:requisicaoDao.listarRequisicoes().size();
		
		List<Requisicao> lista = requisicaoDao.listarRequisicoes().subList(inicio, menorLimite);
		
		String listahtml = "Lista de requisições: </br></br><HR SIZE='2'></hr> ";
		
		for (Requisicao requisicao : lista) {
			listahtml += "</br> Número: " + requisicao.getNumeroFormatado() + "</br> "; 
			listahtml +=  "</br> Data da requisição: " + requisicao.getDataRequisicao() + "</br> ";
			listahtml +=  "</br> Situação: " + requisicao.getStatus()+ "</br> ";
			listahtml +=  "</br> " + requisicao.getPaciente() + "</br> "; 
			listahtml +=  "</br> Laboratório: " + requisicao.getLaboratorio() + "</br> ";
		}
		
		return listahtml;
	}
	
	@GET
	@Path("/pesquisarRequisicao/numero/{numero}")
	@Produces("text/html; charset=UTF-8")
	public String pesquisarRequisicao(@DefaultValue("0") @PathParam("numero") long numero) throws Exception
	{
		Requisicao requisicao = requisicaoDao.pesquisarPorNumero(numero);
		
		if(requisicao.getDataRequisicao()==null){
			return "Requisição número "+ numero+" não encontrada.";
		}
		
		String listahtml = "Requisição: </br></br><HR SIZE='2'></hr> ";
		
		listahtml += "</br> Número: " + requisicao.getNumeroFormatado() + "</br> "; 
		listahtml +=  "</br> Data da requisição: " + requisicao.getDataRequisicao() + "</br> ";
		listahtml +=  "</br> " + requisicao.getPaciente() + "</br> "; 
		listahtml +=  "</br> Laboratório: " + requisicao.getLaboratorio() + "</br> ";
		
		return listahtml;
	}
	
	@POST
	@Path("/consultarRequisicoesAtualizadas")
	@Produces("application/json")
	public RequisicoesTO pesquisarRequisicaoAtualizadas(ListaRequisicaoTO param) throws Exception
	{
		List<Requisicao> requisicoesAtualizadas =  new ArrayList<Requisicao>();
		
		for (Map.Entry<Long, Date> map : param.getMapIdDataUltimaAtualizao().entrySet()) {
		    Requisicao r = requisicaoDao.pesquisarPorNumero(map.getKey());
		    
		    //TODO Adicionar regra para verificar se a requisição foi atualizada após a última atualização.
		    if(r.getStatus()!=SituacaoRequisicao.SOLICITADA){
		    	requisicoesAtualizadas.add(r);
		    }
		}
		 
		 RequisicoesTO lista = new RequisicoesTO();
		 lista.setListaRequisicoes(requisicoesAtualizadas);
		
		return lista;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listar")
	public List<Requisicao> listarRequisicoes(){
		return requisicaoDao.listarRequisicoes();
	}

	public RequisicaoServiceWS() {
		super();
		requisicaoDao = new RequisicaoDaoImpl();
	}
	
	@POST
	@Path("/rejeitar")
	@Produces("text/html; charset=UTF-8")
	public String rejeitar(RequisicaoIdTO requisicaoIdTO) throws Exception {
		try {
			return requisicaoDao.rejeitar(requisicaoIdTO.getNumeroRequisicao()).toString();
		} catch (Exception e) {
			return "Requisicao não encontrada";
		}
		
		
	}
	
	@GET
	@Produces("application/json")
	@Path("rejeicao/{numero}")
	public Requisicao rejeitarRequisicao(@PathParam("numero") long numero) throws Exception{
		return requisicaoDao.rejeitar(numero);		
		
	}
	
}
