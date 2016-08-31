package br.ufrn.sgr.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.ufrn.sgr.dao.RequisicaoDao;
import br.ufrn.sgr.model.Requisicao;

@Path("requisicao") 
public class RequisicaoServiceWS {

	private RequisicaoDao requisicaoDao;
	
	@POST
	@Path("/inserirRequisicao")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserir(Requisicao requisicao) {
		
		requisicaoDao.inserir(requisicao);

		return Response.status(201).entity(requisicao.toString()).build();
		
	}
	
	@POST
	@Path("/atualizarRequisicao")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Requisicao requisicao) {
		
		requisicaoDao.atualizar(requisicao);

		return Response.status(201).entity(requisicao.toString()).build();
		
	}
}
