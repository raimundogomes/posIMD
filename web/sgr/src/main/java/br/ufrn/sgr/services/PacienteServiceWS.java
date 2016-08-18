package br.ufrn.sgr.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.ufrn.sgr.dao.PacienteDao;
import br.ufrn.sgr.dao.impl.PacienteDaoImpl;
import br.ufrn.sgr.exception.PacienteNaoEncontradoException;
import br.ufrn.sgr.model.Paciente;

@Path("paciente") 
public class PacienteServiceWS {
	
	private PacienteDao pacienteDao;
	
	@GET
	@Produces("application/json")
	@Path("prontuario/{numero}")
	public Paciente paciente(@PathParam("numero") int prontuario) throws PacienteNaoEncontradoException{
		return pacienteDao.pesquisarPacientePorNumero(prontuario);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("nome/{nome}")
	public List<Paciente> paciente(@PathParam("nome") String nome) throws PacienteNaoEncontradoException{
		return pacienteDao.pesquisarPacientePorNome(nome);
	}
	
	public void setPacienteDao(PacienteDao pacienteDao) {
		this.pacienteDao = pacienteDao;
	}

	public PacienteServiceWS() {
		super();
		pacienteDao = new PacienteDaoImpl();
	}
	
	@POST
	@Path("/inserirPaciente")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserirPaciente(Paciente paciente) {

		return Response.status(201).entity(paciente.toString()).build();
		
	}

}
