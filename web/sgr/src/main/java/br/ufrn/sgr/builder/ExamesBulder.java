package br.ufrn.sgr.builder;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.sgr.model.Exame;
import br.ufrn.sgr.model.ResultadoExame;
import br.ufrn.sgr.model.TipoExame;



/**
 * Created by Neto on 29/05/2016.
 */
public class ExamesBulder {

public static long getNumeroGerador() {
    	
		return numeroGerador ;
	}
    
    public static void incrementarNumeroGerador(){
    	numeroGerador++;
    }


	private static long numeroGerador = 1;
    
	public List<Exame> getListaExames(){

        return new ArrayList<Exame>();

    }

    public Exame adicionaExameSangue( ){
       return  adicionaExame(TipoExame.SANGUE);

    }

    public Exame adicionaExameSangueResultadoDefinitivo( ){
        Exame exame =   adicionaExame(TipoExame.SANGUE);
        exame.setResultadoExame(ResultadoExame.NEGATIVO);

        exame.setResultadoCompleto("Ausência de crescimento bacteriano na amostra analisada após 7 dias de incubação");
        return exame;

    }



    public Exame adicionaExameUrina(){
        return  adicionaExame( TipoExame.URINA);

    }

    public Exame adicionaExame(TipoExame tipo ){

       return new Exame(tipo);

    }
}
