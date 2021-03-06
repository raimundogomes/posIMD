package com.imd030.sgr.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by netou on 31/10/2016.
 */

public class SgrBD  extends SQLiteOpenHelper  {

    private static final String DATABASE_NAME = "sgr_bd";

    private static final String CREATE_TABLE_PACIENTE =
                "CREATE TABLE PACIENTE " +
                        "(ID INTEGER PRIMARY KEY," +
                        "NOME TEXT," +
                        "NOME_MAE TEXT(150)," +
                        "DATA_NASCIMENTO TEXT(10)," +
                        "CPF TEXT(11)," +
                        "CNS TEXT(11)," +
                        "PRONTUARIO INTEGER," +
                        "TELEFONE TEXT(14)," +
                        "EMAIL TEXT" +
                       "); ";

    private static final String CREATE_TABLE_REQUISICAO =
            " CREATE TABLE REQUISICAO " +
                    "(ID INTEGER PRIMARY KEY," +
                    " NUMERO INTEGER, " +
                    "ID_PACIENTE INTEGER, " +
                    "DATA_REQUISICAO TEXT(20), " +
                    "ID_SITUACAO INTEGER, " +
                    "ID_LABORATORIO INTEGER, " +
                    "DATA_ULTIMA_ATUALIZACAO TEXT(20)" +
                    "); ";

    private static final String CREATE_TABLE_EXAME =
            " CREATE TABLE EXAME " +
                    "(ID INTEGER PRIMARY KEY," +
                    "DATA_AMOSTRA TEXT(20), " +
                    "ID_SITUACAO INTEGER, " +
                    "ID_REQUISICAO INTEGER, " +
                    "RESULTADO TEXT ); ";

    private static final String INSERT_PACIENTES = "INSERT INTO PACIENTE VALUES (";

    SgrBD(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
       public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PACIENTE);
        db.execSQL(CREATE_TABLE_REQUISICAO);
        db.execSQL(CREATE_TABLE_EXAME);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
            db.execSQL("DROP TABLES");
            Log.d("Teste", CREATE_TABLE_REQUISICAO.toString());
            db.execSQL(CREATE_TABLE_PACIENTE);
            db.execSQL(CREATE_TABLE_REQUISICAO);
            db.execSQL(CREATE_TABLE_EXAME);
        }
}


