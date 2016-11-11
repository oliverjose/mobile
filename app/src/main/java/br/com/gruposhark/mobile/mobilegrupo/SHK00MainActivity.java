package br.com.gruposhark.mobile.mobilegrupo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import model.Celular;
import model.Modelo;

public class SHK00MainActivity extends AppCompatActivity{

    Celular celular;
    //Celular celular;
    //ArrayList<Celular> celulars;
    ArrayList<Celular>varCelular;


    //private static final int PERMISSION_READ_STATE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shk00_main);
/*
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // We do not have this permission. Let's ask the user
        }

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_READ_STATE);

        */

        // Fazer a verificação de rede no dispositivo
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        String s = "Conexão: ";
        if(wifi.isConnected()){
            s += "Conectando via Wi-Fi";
        }else if(mobile.isConnected()){
            s += "Conectando via Dados Móvel";
        }else {
            s += "Sem conexão com internet";
            Intent intentSHK00 = new Intent(this, SHK99ErroActivity.class);
            intentSHK00.putExtra("ERRO", s);
            startActivity(intentSHK00);
            finish();
        }
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("SHK00Main -> ", "onStart");

        TelephonyManager telephonyManager = (TelephonyManager)
                getSystemService(Context.TELEPHONY_SERVICE);
        varCelular = new ArrayList<Celular>();

        celular = new Celular(
                telephonyManager.getDeviceId(),
                telephonyManager.getSimSerialNumber()
        );
        varCelular.add(celular);
    }

    public void login_onClick(View view) {
        Log.e("SHK00Main -> ", "menu_onClick");
        Intent intentSHK01 = new Intent(this, SHK01LoginActivity.class);
        intentSHK01.putParcelableArrayListExtra("CELULAR", varCelular);
        startActivity(intentSHK01);
        finish();
    }

/*
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_READ_STATE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted!
                    // you may now do the action that requires this permission

                } else {
                    // permission denied
                }
                return;
            }

        }
    }

    */
}
