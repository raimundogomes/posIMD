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
		listaPacientes.put(i, new Paciente(i++, "Machado de Assis", "21/06/1838", "Maria Machado da Câmara", "00079265403", "000023838"));
		listaPacientes.put(i, new Paciente(i++, "José de Alencar", "21/06/1867", "Guilhermina Siva", "98779265403", "000923838"));
		listaPacientes.put(i, new Paciente(i++, "Lima Barreto", "21/06/1890", "Clarice Lispector", "89379215403", "10023838"));
		listaPacientes.put(i, new Paciente(i++, "Raquel de Queiroz","17/11/1910", "Maria da Silva", "39379265403", ""));
		listaPacientes.put(i, new Paciente(i++, "Cecília Meireles", "10/06/1985", "Fabiana Gomes Nunes", "49379265403", ""));
		listaPacientes.put(i, new Paciente(i++, "Cecília Meireles" ,"21/06/1990", "Maria da Silva", "59379265403", ""));
		listaPacientes.put(i, new Paciente(i++, "Lima Barreto", "Maria da Silva","21/06/1890", "89379665403", ""));
		listaPacientes.put(i, new Paciente(i++, "Clarice Lispector" , "Maria da Silva","10/12/1920",  "87379265403", ""));
		listaPacientes.put(i, new Paciente(i++, "Lima Barreto", "Raquel de Queiroz" ,"21/01/1991", "89379065403", ""));
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
