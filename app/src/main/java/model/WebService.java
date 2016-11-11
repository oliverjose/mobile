package model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import util.SoapSerEnv;

/**
 * Created by jose.oliveira on 10/11/2016.
 */

public class WebService extends AsyncTask<String, Object, String> {

    //private final String NAMESPACE = "http://177.11.31.87:23973/";
    private final String NAMESPACE = "http://10.1.17.100:8013/";
    private final String SOAP_SERVICE = "ws/WSMOBILE01000.apw";
    private final String URL = NAMESPACE + SOAP_SERVICE;
    private final String METHOD = "SMOBA01001";
    private final String SOAP_ACTION = NAMESPACE + METHOD;

    private SoapObject request = null;
    private SoapObject results = null;
    private SoapObject response = null;
    private SoapObject liberacao = null;
    private SoapObject libera = null;
    private SoapSerEnv envelope = null;
    private HttpTransportSE androidHttpTransport = null;
    boolean erro = false;

    public WebService() {

    }

    @Override
    protected String doInBackground(String... params) {
        Log.e("SHK01LoginActivity/WebService -> ", "doInBackground");


        try {
            getSMOBA01000(params[0], params[1], params[2]);
            //Atualiza a interface através do onProgressUpdate
        } catch (Exception e) {
            e.printStackTrace();
            //enviarErro(respErro);
        }
        return null; //

    }
    @Override
    protected void onPreExecute() {
        Log.e("SHK01LoginActivity/WebService -> ", "onPreExecute");

        //Context contexto = getApplicationContext();

    }

    @Override
    protected void onPostExecute(String strings) {
        Log.e("SHK01LoginActivity/WebService -> ", "onPostExecute");
    }

    private void getSMOBA01000(String _usuario, String _senha, String _imei){
        Log.e("SHK01LoginActivity/WebService -> ", "getSMOBA01000");

        request = new SoapObject(NAMESPACE, METHOD);
        envelope = new SoapSerEnv(SoapEnvelope.VER11);
        androidHttpTransport = new HttpTransportSE(URL);

        envelope.implicitTypes = true;
        envelope.setAddAdornments(false);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        request.addProperty("_CLOGIN", _usuario);
        request.addProperty("_CMD5", _senha);
        request.addProperty("_CIMEI", _imei);



        try {
            androidHttpTransport.getServiceConnection().setRequestProperty("Connection", "close");
            System.setProperty("http.keepAlive", "false");

            androidHttpTransport.call(SOAP_ACTION, envelope);

            results = (SoapObject) envelope.bodyIn;
            response = (SoapObject) envelope.getResponse();
            liberacao = (SoapObject) results.getProperty(0);

            for (int i = 0; i < liberacao.getPropertyCount(); i++) {
                libera = (SoapObject) liberacao.getProperty(i);
                Log.d("CONEXAO_WS_EFETUADA: ", response.toString());
                libera.getProperty(i).toString();

                libera.getProperty(2).toString(); // IdUser 2
                libera.getProperty(3).toString(); // Login 3
                libera.getProperty(4).toString();  // Nome 4
                libera.getProperty(1).toString();  // Email 1
                libera.getProperty(5).toString();  // Tema 5
                libera.getProperty(0).toString();  // Conexao 0
            }
            Log.v("DADOS_WS_CAPTURADO ", response.toString());
        }
        catch(Exception e){
            Log.e("SHK01LoginActivity/WebService -> ", "Error on soapObjectData() " + e.getMessage());
            if(envelope.bodyIn != null){

            }else {
                Class c = e.getClass();
            }
            erro = true;//1

        }
        // 2
    }
}


