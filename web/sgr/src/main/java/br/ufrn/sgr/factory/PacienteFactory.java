package br.ufrn.sgr.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufrn.sgr.exception.PacienteNaoEncontradoException;
import br.ufrn.sgr.model.Paciente;

public class PacienteFactory {
	
	private static Map<Long, Paciente> listaPacientes =  new HashMap<Long,Paciente>();
	
	static{
		long  i = 1;
		listaPacientes.put(i, new Paciente(i++, "Machado de Assis", "21/06/1838", "Maria Machado da Câmara", "00079265403", "000023838","machado.assis@literatura.br", "998090987"));
		listaPacientes.put(i, new Paciente(i++, "José de Alencar", "21/06/1867", "Guilhermina Siva", "98779265403", "000923838","jose.alencar@literatura.br", "998090987"));
		listaPacientes.put(i, new Paciente(i++, "Lima Silva Barreto", "21/06/1890", "Maria Silva Barreto", "89379215403", "10023838","lima.silva@literatura.br", "998090987"));
		listaPacientes.put(i, new Paciente(i++, "Raquel de Queiroz","17/11/1910", "Maria da Silva", "39379265403", "","raquelzinha@literatura.br", "998090987"));
		listaPacientes.put(i, new Paciente(i++, "Cecília Meireles", "10/06/1985", "Fabiana Gomes Nunes", "49379265403", "","cecilia@literatura.br", "998090987"));
		listaPacientes.put(i, new Paciente(i++, "Cecília Meireles" ,"21/06/1990", "Maria da Silva", "59379265403", "","celcilia.meireles@literatura.br", "998090987"));
		listaPacientes.put(i, new Paciente(i++, "Lima Barreto Junior","21/06/1890", "Maria da Silva", "89379665403", "","lima.junior@literatura.br", "998090987"));
		listaPacientes.put(i, new Paciente(i++, "Clarice Lispector" ,"10/12/1920", "Maria da Silva",  "87379265403", "","clarice.lispector@literatura.br", "998090987"));
		listaPacientes.put(i, new Paciente(i++, "Lima Barreto" ,"21/01/1991","Raquel Silva",  "89379065403", "","lima.barreto@literatura.br", "998090987"));
	}
	
	
	public static Paciente getByNumeroProntuario(long prontuario) throws PacienteNaoEncontradoException{
		
		if(listaPacientes.containsKey(prontuario)){
			return listaPacientes.get(prontuario);
		}
		
		throw new PacienteNaoEncontradoException(prontuario);
		
	}


	public static List<Paciente> retornaPorNome(String nomePaciente) {
		
		if(nomePaciente==null){
			throw new IllegalArgumentException("Nome não informado");
		}
		
		ArrayList<Paciente> lista = new ArrayList<Paciente>(listaPacientes.values());
		
		List<Paciente> listaFiltradas = new ArrayList<Paciente>();
		
		for(Paciente paciente: lista){
            if(paciente.getNome().toLowerCase().contains(nomePaciente.toLowerCase())){
            	listaFiltradas.add(paciente);
            }
        }
		
		return listaFiltradas;
	}

}
