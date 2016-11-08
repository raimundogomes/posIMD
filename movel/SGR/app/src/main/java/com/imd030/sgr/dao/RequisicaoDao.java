package com.imd030.sgr.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.imd030.sgr.entity.Laboratorio;
import com.imd030.sgr.entity.Requisicao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by netou on 07/11/2016.
 */

public class RequisicaoDao {

    private BaseDao baseDao;

    public RequisicaoDao(Context contexto) {

        baseDao = BaseDao.getInstance(contexto);
    }

    public void insert(Requisicao requisicao) {

        ContentValues values = new ContentValues(3);

        values.put("NUMERO", requisicao.getNumero());
        values.put("ID_SITUACAO", requisicao.getStatus().getCodigo());
        values.put("ID_LABORATORIO", requisicao.getLaboratorio().getId());

        if(requisicao.getPaciente()!=null && requisicao.getPaciente().getProntuario()!=null){
            values.put("ID_PACIENTE", requisicao.getPaciente().getId());
        }

        values.put("DATA_REQUISICAO", BaseDao.FORMATE_DATE.format(requisicao.getDataRequisicao()));

        long id = baseDao.getDatabase().insert("requisicao", null, values);

        requisicao.setId(id);


    }

    public void delete(Requisicao requisicao) {

        int alt = baseDao.getDatabase().delete("requisicao", "ID=?",
                new String[] { String.valueOf(requisicao.getId()) });

    }

    public List<Requisicao> listar(){
        List<Requisicao> requisicoes = new ArrayList<Requisicao>();
        Cursor c = baseDao.getDatabase().query("REQUISICAO", Requisicao.COLUNAS,
                 null, null, null, null, null);
        if (c.moveToFirst()){
            do{
                Requisicao requisicao = new Requisicao();
                requisicao.setId(c.getLong(0));
                requisicao.setNumero(c.getInt(1));

                requisicao.setSituacao(c.getInt(2));

                requisicao.setLaboratorio(Laboratorio.getLaboratorioById(c.getInt(3)));

                Date data;

                try {
                    data = BaseDao.FORMATE_DATE.parse(c.getString(2));
                } catch (ParseException e) {
                    data = new Date();
                }

                requisicao.setDataRequisicao(data);
                requisicoes.add(requisicao);
            }while(c.moveToNext());
        }
        c.close();
        return requisicoes;
    }

}
