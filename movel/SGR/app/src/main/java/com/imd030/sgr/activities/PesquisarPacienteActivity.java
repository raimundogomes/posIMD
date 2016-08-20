package com.imd030.sgr.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.imd030.sgr.R;

public class PesquisarPacienteActivity extends Activity {



    final String url = "http://locahost:8080/sgr/service/paciente/prontuario/1000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_paciente);


    }

    public void pesquisar(View v){
        String prontuario =  ((TextView)findViewById(R.id.text_prontuario)).getText().toString();
        String cpf =  ((TextView)findViewById(R.id.text_cpf)).getText().toString();
        String nome = ((TextView) findViewById(R.id.text_nome_paciente)).getText().toString();

        if( !"".equals(prontuario) || !"".equals(cpf) || !"".equals(nome) ){

//            pesquisarPaciente(prontuario);
        }
        else{
            Toast toast = Toast.makeText(this, "Preencha o prontuário, CPF ou nome do paciente!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

}
