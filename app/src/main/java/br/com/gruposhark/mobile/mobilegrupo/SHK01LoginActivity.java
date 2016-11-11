package br.com.gruposhark.mobile.mobilegrupo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;

import model.Celular;

import model.Usuario;
import model.WebService;
import util.Converte;

public class SHK01LoginActivity extends AppCompatActivity {

    EditText editTextSHK01Login;
    EditText editTextSHK01Senha;
    Button buttonSHK01Entrar;
    ProgressBar progressBarSHK01;

    ArrayList<Celular> varCelular;
    Usuario usuario;
    ArrayList<Usuario>varUsuario;
    WebService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shk01_login);
        Log.e("SHK01LoginActivity -> ", "onCreate");

        editTextSHK01Login = (EditText) findViewById(R.id.editTextSHK01Login);
        editTextSHK01Senha = (EditText) findViewById(R.id.editTextSHK01Senha);
        buttonSHK01Entrar  = (Button)   findViewById(R.id.buttonSHK01Entrar);
        progressBarSHK01   = (ProgressBar) findViewById(R.id.progressBarSHK01);
        progressBarSHK01.setVisibility(View.GONE);

        Intent it = getIntent();
        varCelular = it.getParcelableArrayListExtra("CELULAR"); // Contém dados do celular

        usuario = new Usuario();
        varUsuario = new ArrayList<Usuario>();
        service = new WebService();
    }

    public void menu_onClick(View view){

        final Converte converteSenha = new Converte();

        try {
            String usuario = editTextSHK01Login.getText().toString();
            String senha = converteSenha.convertPasswordToMD5(editTextSHK01Senha.getText().toString());
            String imei = varCelular.get(0).getImei();

            service.execute(usuario, senha, imei);

            progressBarSHK01.setVisibility(View.VISIBLE);


        }catch (Exception e){
            Class c = e.getClass();
            String respErro = c.getName();
        }
    }

}
