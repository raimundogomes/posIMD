package br.ufrn.sgr.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufrn.sgr.exception.PacienteNaoEncontradoException;
import br.ufrn.sgr.model.Paciente;

public class PacienteFactory {
	
	private static Map<Integer, Paciente> listaPacientes =  new HashMap<Integer,Paciente>();
	
	static{
		int  i = 1000;
		listaPacientes.put(i, new Paciente(i++,"21/06/1838", "Machado de Assis", "Maria Machado da Câmara", "00079265403", "000023838"));
		listaPacientes.put(i, new Paciente(i++,"21/06/1867", "José de Alencar", "Guilhermina Siva", "98779265403", "000923838"));
		listaPacientes.put(i, new Paciente(i++,"21/06/1890", "Lima Barreto", "Maria da Silva", "89379265403", "10023838"));
	}
	
	
	public static Paciente getByNumeroProntuario(int prontuario) throws PacienteNaoEncontradoException{
		
		if(listaPacientes.containsKey(prontuario)){
			return listaPacientes.get(prontuario);
		}
		
		throw new PacienteNaoEncontradoException(prontuario);
		
	}


	public static List<Paciente> retornaPorNome(String nome) {
		return new ArrayList<Paciente>(listaPacientes.values());
	}

}
