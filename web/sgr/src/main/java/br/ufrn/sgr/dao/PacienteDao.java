package br.ufrn.sgr.dao;

import java.util.List;

import br.ufrn.sgr.exception.PacienteNaoEncontradoException;
import br.ufrn.sgr.model.Paciente;

public interface PacienteDao {

	/**
	 * Método retorna um paciente com base no número do prontuário.
	 * @param prontuario Número do prontuário do paciente.
	 * @return {@link Paciente}.
	 * @throws PacienteNaoEncontradoException 
	 */
	Paciente pesquisarPacientePorNumero(int prontuario) throws PacienteNaoEncontradoException;

	List<Paciente> pesquisarPacientePorNome(String nome);

	Paciente pesquisarPacientePorCpf(String cpf);

}
