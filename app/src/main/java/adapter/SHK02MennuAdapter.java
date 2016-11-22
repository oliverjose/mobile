/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import br.com.gruposhark.mobile.mobilegrupo.R;
import model.Mennu;

/**
 * Created by Admin on 30/10/2016.
 */
public class SHK02MennuAdapter extends BaseAdapter{

    private Context context;
    private List<Mennu>mennuList;
    //private LayoutInflater layoutInflater;

    public SHK02MennuAdapter(Context context, List<Mennu> mennuList) {
        this.context = context;
        this.mennuList = mennuList;
        //this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {

        return mennuList.size();
    }

    @Override
    public Object getItem(int position) {

        return mennuList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Mennu mennu = mennuList.get(position);

        View linha = LayoutInflater.from(context).inflate(R.layout.layout_shk02_mennu,null);

        //Button buttonSHK02CPO04 = (Button) linha.findViewById(R.id.buttonSHK02CPO04);
        TextView textViewMenu = (TextView) linha.findViewById(R.id.textViewMenu);

        textViewMenu.setText(mennu.getCpo04());
        return linha;
    }
}
