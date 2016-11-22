
package br.com.gruposhark.mobile.mobilegrupo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.SHK02MennuAdapter;
import model.Celular;
import model.Mennu;
import model.Usuario;

import android.widget.AdapterView.OnItemClickListener;

public class SHK02MenuActivity extends AppCompatActivity {
    ArrayList<Celular> varCelular;
    ArrayList<Usuario>varUsuario;
    ArrayList<Mennu>mennus;


    Mennu mennu;
    SHK02MennuAdapter adapter;

    ListView listViewSHK02Menu;
    TextView textViewMenu;
    LinearLayout linearLayoutMenu;
    Button buttonSHK02Sair;
    ProgressBar progressBarSHK02;
    ImageView imageViewSHK02Grupo;
    TextView textViewSHK02Menu;
    TextView textViewSHK02GrupoShark;

    private String conexao;
    private String respErro = null;

    int c = Color.CYAN;
    int shk;

    SHK98ServicoActivity shk98Servico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shk02_menu);

        listViewSHK02Menu = (ListView) findViewById(R.id.listViewSHK02Menu);
        textViewMenu = (TextView) findViewById(R.id.textViewMenu);


        Intent itSHK02 = getIntent();
        varCelular = itSHK02.getParcelableArrayListExtra("CELULAR"); // Contém dados do celular
        varUsuario = itSHK02.getParcelableArrayListExtra("USUARIO"); // Contém dados do celular
        mennus = itSHK02.getParcelableArrayListExtra("MENNU");


        adapter = new SHK02MennuAdapter(SHK02MenuActivity.this,mennus);
        listViewSHK02Menu.setAdapter((ListAdapter) adapter);


        listViewSHK02Menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //progressBarSHK02.setVisibility(View.VISIBLE);
                listViewSHK02Menu.getChildAt(position).setBackgroundColor(Color.blue(position));
                Intent intentSHK02 = new Intent(getBaseContext(), SHK03MenuResumoActivity.class);
                String cpo03 = "";
                String cpo04 = "";
                //int _id = position + 1;
                cpo03 = mennus.get(position).getCpo03();
                cpo04 = mennus.get(position).getCpo04();
                int pos = cpo04.indexOf("(");
                cpo04 = cpo04.substring(0, pos);

                intentSHK02.putExtra("CPO03", cpo03);
                intentSHK02.putExtra("CPO04", cpo04);
                intentSHK02.putParcelableArrayListExtra("USUARIO", varUsuario);
                intentSHK02.putParcelableArrayListExtra("MENNU", mennus);
                startActivity(intentSHK02);
            }
        });

    }
/*
    public void myClickHandler(View v)
    {

        //reset all the listView items background colours
        //before we set the clicked one..

        //ListView listViewSHK02Menu = getListView();
        for (int i=0; i < listViewSHK02Menu.getChildCount(); i++)
        {
            listViewSHK02Menu.getChildAt(i).setBackgroundColor(Color.BLUE);
        }


        //get the row the clicked button is in
        LinearLayout vwParentRow = (LinearLayout)v.getParent();

        TextView child = (TextView)vwParentRow.getChildAt(0);
        Button btnChild = (Button)vwParentRow.getChildAt(1);
        btnChild.setText(child.getText());
        btnChild.setText("I've been clicked!");

        int c = Color.CYAN;

        vwParentRow.setBackgroundColor(c);
        vwParentRow.refreshDrawableState();
    }

*/
}
