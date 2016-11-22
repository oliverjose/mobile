package br.com.gruposhark.mobile.mobilegrupo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import model.Celular;
import model.Mennu;
import model.Usuario;
import util.SoapSerEnv;

public class SHK98ServicoActivity extends AppCompatActivity {

    static int SHARK_SERVICO_LOGIN = 100;

    private TextView textViewSHK98Servico;
    private ProgressBar progressBarSHK98Servico;

    private Context context;
    private String url;
    private String porta;
    private String servico;
    private String metodo;


    private int shk;
    private String _usuario;
    private String _senha;
    private String _imei;
    private String _conexao;

    Usuario usuario;
    Mennu mennu;
    ArrayList<Mennu>mennus;
    ArrayList<Usuario> varUsuario;
    ArrayList<Celular> varCelular;
    WebService ws;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shk98_servico);

        textViewSHK98Servico = (TextView) findViewById(R.id.textViewSHK98Servico);
        progressBarSHK98Servico = (ProgressBar) findViewById(R.id.progressBarSHK98Servico);
        //progressBarSHK98Servico.setVisibility(View.GONE);

        ws = new WebService();
        mennus = new ArrayList<Mennu>();

        Intent itSHK98 = getIntent();
        shk = itSHK98.getIntExtra("SHK", 0);
        varCelular = itSHK98.getParcelableArrayListExtra("CELULAR"); // Contém dados do celular
        _usuario = itSHK98.getStringExtra("USUARIO");
        _senha = itSHK98.getStringExtra("SENHA");
        _imei = itSHK98.getStringExtra("IMEI");
        _conexao = itSHK98.getStringExtra("CONEXAO");
    }

    @Override
    protected void onResume() {
        super.onResume();

        progressBarSHK98Servico.setVisibility(View.GONE);

        switch (shk){
            case 100:
                shk98Servico("10.1.17.100", "8013", "WSMOBILE01000", "SMOBA01001");
                ws.execute(_usuario, _senha, _imei);
                break;
            case 200:
                shk98Servico("10.1.17.100", "8013", "WSMOBILE02000", "SMOBA02001");
                ws.execute(_conexao);
                break;
        }
    }

    public void shk98Servico(String _url, String _porta, String _servico, String _metodo){
        this.url = _url;
        this.porta = _porta;
        this.servico = _servico;
        this.metodo = _metodo;
    }

    public class WebService extends AsyncTask<String, Object, String> {

        private String NAMESPACE;
        private String SOAP_SERVICE;
        private String URL;
        private String METHOD;
        private String SOAP_ACTION;

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


        @SuppressWarnings("WrongThread")
        @Override
        protected String doInBackground(String... params) {
            switch (shk){
                case 100:
                    textViewSHK98Servico.setText("Aguarde, conectando!");
                    progressBarSHK98Servico.setVisibility(View.VISIBLE);
                    getSMOBA01000(params[0],params[1],params[2]);
                    break;
                case 200:
                    getSMOBA02000(params[0]);
                    break;
            }

            int n = 3;
            int i = 0;
            while(i <= n) {
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                //Notifica o Android de que ele precisa
                //fazer atualizações na
                //tela e chama o método onProgressUpdate
                //para fazer a atualização da interface gráfica
                //passando o valor do
                //contador como parâmetro.
                publishProgress(n);
                n--;
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            NAMESPACE = "http://"+url+":"+porta+"/";
            SOAP_SERVICE = "ws/"+servico+".apw";
            URL = NAMESPACE + SOAP_SERVICE;
            METHOD = metodo;
            SOAP_ACTION = NAMESPACE + METHOD;

        }

        @Override
        protected void onPostExecute(String s) {
            //
            switch (shk){
                case 100:
                    progressBarSHK98Servico.setVisibility(View.GONE);
                    Log.e("SHK98Serviço -> ", "onPostExecute");
                    textViewSHK98Servico.setText("Conexão Efetuada...");

                    Intent intentSHK01 = new Intent(SHK98ServicoActivity.this, SHK98ServicoActivity.class);

                    intentSHK01.putExtra("SHK", 200);
                    intentSHK01.putExtra("CONEXAO", varUsuario.get(0).getConexao());
                    startActivity(intentSHK01);
                    finish();
                    break;
                case 200:
                    textViewSHK98Servico.setText("Menu atualizado...");
                    progressBarSHK98Servico.setVisibility(View.GONE);
                    Intent intentSHK02 = new Intent(SHK98ServicoActivity.this, SHK02MenuActivity.class);
                    intentSHK02.putExtra("SHK", 200);
                    intentSHK02.putParcelableArrayListExtra("CELULAR", varCelular);
                    intentSHK02.putParcelableArrayListExtra("USUARIO", varUsuario);
                    intentSHK02.putParcelableArrayListExtra("MENNU", mennus);
                    startActivity(intentSHK02);
                    finish();
                    break;
            }

        }

        @Override
        protected void onProgressUpdate(Object... values) {
            //super.onProgressUpdate(values);
            //textViewSHK98Servico.setText(String.valueOf(values[0]));
            switch (shk){
                case 100:
                    textViewSHK98Servico.setText("Conexão efetuada..." + String.valueOf(values[0]));
                    break;
                case 200:
                    textViewSHK98Servico.setText("Atualizando menu..." + String.valueOf(values[0]));
                    break;
            }
        }


        public void getSMOBA01000(String _usuario, String _senha, String _imei){
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

                varUsuario = new ArrayList<Usuario>();
                for (int i = 0; i < liberacao.getPropertyCount(); i++) {
                    libera = (SoapObject) liberacao.getProperty(i);
                    Log.d("CONEXAO_WS_EFETUADA: ", response.toString());
                    libera.getProperty(i).toString();
                    usuario = new Usuario(
                            libera.getProperty(2).toString(), // IdUser 2
                            libera.getProperty(3).toString(), // Login 3
                            libera.getProperty(4).toString(), // Nome 4
                            libera.getProperty(1).toString(), // Email 1
                            libera.getProperty(5).toString(), // Tema 5
                            libera.getProperty(0).toString()  // Conexao 0
                    );

                    varUsuario.add(usuario);

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

        public void getSMOBA02000(String _conexao){
            Log.e("SHK02MenuActivity -> ", "getIdentificadorSMOBA02000");


            request = new SoapObject(NAMESPACE, METHOD);
            envelope = new SoapSerEnv(SoapEnvelope.VER11);
            androidHttpTransport = new HttpTransportSE(URL);

            envelope.implicitTypes = true;
            envelope.setAddAdornments(false);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            request.addProperty("_CCONEXAO", _conexao);

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

                    mennu = new Mennu(
                    libera.getProperty(0).toString(), // LIBERA
                    libera.getProperty(1).toString(), // LIBERACAO
                    libera.getProperty(2).toString(), // DEVVENDA
                    libera.getProperty(3).toString(), // DEV. VENDA
                    libera.getProperty(4).toString(), // 1
                    libera.getProperty(5).toString() // 0
                    );
                    mennus.add(mennu);
                }
                Log.v("DADOS_WS_CAPTURADO ", response.toString());
            }
            catch(Exception e){
                Log.e("SHK02MenuActivity", "Error on soapObjectData() " + e.getMessage());
                if(envelope.bodyIn != null){

                }else {
                    Class c = e.getClass();
                }
                erro = true;//1
            }
        }
    }
}
