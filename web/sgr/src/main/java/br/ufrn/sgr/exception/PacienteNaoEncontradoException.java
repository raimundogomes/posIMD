package br.ufrn.sgr.exception;

public class PacienteNaoEncontradoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private int prontuario;

	public PacienteNaoEncontradoException(int prontuario) {
		super();
		this.prontuario = prontuario;
	}

	
	
	@Override
	public String getMessage() {
		
		return "Paciente não encontrado com número de prontuário: " + prontuario;
	}
	
}
