package br.ufrn.sgr.exception;

public class PacienteNaoEncontradoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private long prontuario;

	public PacienteNaoEncontradoException(long prontuario) {
		super();
		this.prontuario = prontuario;
	}

	
	
	@Override
	public String getMessage() {
		
		return "Paciente não encontrado com número de prontuário: " + prontuario;
	}
	
}
