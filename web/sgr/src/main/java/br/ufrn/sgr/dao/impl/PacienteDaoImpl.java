package br.ufrn.sgr.dao.impl;

import java.util.List;

import br.ufrn.sgr.dao.PacienteDao;
import br.ufrn.sgr.exception.PacienteNaoEncontradoException;
import br.ufrn.sgr.factory.PacienteFactory;
import br.ufrn.sgr.model.Paciente;

public class PacienteDaoImpl implements PacienteDao {

	@Override
	public Paciente pesquisarPacientePorNumero(int prontuario) throws PacienteNaoEncontradoException {
		return PacienteFactory.getByNumeroProntuario(prontuario);
	}

	@Override
	public List<Paciente> pesquisarPacientePorNome(String nome) {
		return PacienteFactory.retornaPorNome(nome);
	}

}
