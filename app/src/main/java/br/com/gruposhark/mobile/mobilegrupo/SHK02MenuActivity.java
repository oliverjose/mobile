
package br.com.gruposhark.mobile.mobilegrupo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import model.Celular;
import model.Usuario;

public class SHK02MenuActivity extends AppCompatActivity {
    ArrayList<Celular> varCelular;
    ArrayList<Usuario>varUsuario;

    int shk;

    SHK98ServicoActivity shk98Servico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shk02_menu);

        Intent itSHK02 = getIntent();
        varCelular = itSHK02.getParcelableArrayListExtra("CELULAR"); // Contém dados do celular
        varUsuario = itSHK02.getParcelableArrayListExtra("USUARIO"); // Contém dados do celular




        Intent shk98Servico = new Intent(this, SHK98ServicoActivity.class);
        shk98Servico.putExtra("SHK", 200);
        shk98Servico.putExtra("CONEXAO", varUsuario.get(0).getConexao());
        startActivity(shk98Servico);
    }
}
