package com.imd030.sgr.builder;

import com.imd030.sgr.entiitys.Exame;
import com.imd030.sgr.entiitys.ResultadoExame;
import com.imd030.sgr.entiitys.SituacaoAmostra;
import com.imd030.sgr.entiitys.TipoExame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neto on 29/05/2016.
 */
public class ExamesBulder {

    public List<Exame> getListaExames(){

        return new ArrayList<Exame>();

    }

    public Exame adicionaExameSangue( ){
       return  adicionaExame(TipoExame.SANGUE);

    }

    public Exame adicionaExameSangueResultadoDefinitivo( ){
        Exame exame =   adicionaExame(TipoExame.SANGUE);
        exame.setResultadoExame(ResultadoExame.NEGATIVO);
        exame.getAmostra().setSituacaoAmostra(SituacaoAmostra.LIBERADA);

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
