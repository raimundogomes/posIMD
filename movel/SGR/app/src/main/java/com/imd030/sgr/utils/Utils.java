package com.imd030.sgr.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.imd030.sgr.comparator.Paciente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by allanromanato on 11/4/15.
 */
public class Utils {

    public Paciente getInformacao(String end){
        String json;
        Paciente retorno;
        json = NetworkUtils.getJSONFromAPI(end);
        Log.i("Resultado", json);
        retorno = parseJson(json);

        return retorno;
    }

    private Paciente parseJson(String json){
        try {
            Paciente pessoa = new Paciente();

            JSONObject jsonObj = new JSONObject(json);

            Log.i("T", json);
            JSONArray array = jsonObj.getJSONArray("results");

            JSONObject objArray = array.getJSONObject(0);

            JSONObject obj = objArray.getJSONObject("user");
            //Atribui os objetos que estão nas camadas mais altas
            pessoa.setNome(obj.getString("nome"));
            pessoa.setNomeMae(obj.getString("nomeMae"));
            pessoa.setProntuario(obj.getInt("prontuario"));
            pessoa.setCns(obj.getString("cns"));

            pessoa.setDataNascimento(obj.getString("dataNascimento"));



            return pessoa;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    private Bitmap baixarImagem(String url) {
        try{
            URL endereco;
            InputStream inputStream;
            Bitmap imagem; endereco = new URL(url);
            inputStream = endereco.openStream();
            imagem = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return imagem;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
