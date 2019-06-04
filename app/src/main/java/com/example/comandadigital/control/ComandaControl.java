package com.example.comandadigital.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renatodasilva.exemploormlite.R;
import com.example.renatodasilva.exemploormlite.dao.ItemComandaDao;
import com.example.renatodasilva.exemploormlite.model.Comanda;
import com.example.renatodasilva.exemploormlite.model.ItemComanda;
import com.example.renatodasilva.exemploormlite.view.AddItemComandaActivity;

import java.sql.SQLException;

public class ComandaControl {
    private Activity activity;
    private TextView tvTotal;
    private ListView lvItensComanda;

    private ArrayAdapter<ItemComanda> adapterItemComanda;
    private ItemComandaDao itemComandaDao;
    private ItemComanda itemComanda;
    private Comanda comanda;

    public ComandaControl(Activity activity) {
        this.activity = activity;
        comanda = (Comanda) activity.getIntent().getSerializableExtra("pcomanda");

        itemComandaDao = new ItemComandaDao(activity);
        initComponents();
    }

    private void initComponents(){
        tvTotal = activity.findViewById(R.id.tvTotal);
        lvItensComanda = activity.findViewById(R.id.lvItensComanda);
        configurarListView();
    }

    private void configurarListView(){
        adapterItemComanda = new ArrayAdapter<>(
                activity,
                android.R.layout.simple_list_item_1,
                comanda.getListaItensComanda()
        );
        lvItensComanda.setAdapter(adapterItemComanda);
        cliqueCurto();
    }

    private void cliqueCurto(){
        lvItensComanda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemComanda = adapterItemComanda.getItem(position);
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Alterando item da comanda");
                alerta.setPositiveButton("+1", null);
                alerta.setNegativeButton("-1", null);
                alerta.setNeutralButton("Fechar", null);
                alerta.show();
            }
        });
    }

    public void addItemComandaAction(){
        Intent it = new Intent(activity, AddItemComandaActivity.class);
        activity.startActivityForResult(it, 777);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==777 && resultCode==activity.RESULT_OK){
            itemComanda = (ItemComanda) data.getSerializableExtra("pitem");
            itemComanda.setComanda(comanda);
            try {
                itemComandaDao.getDao().create(itemComanda);
                adapterItemComanda.add(itemComanda);
                itemComanda = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(resultCode == activity.RESULT_CANCELED) {
            Toast.makeText(activity, "Cancelou", Toast.LENGTH_SHORT).show();
        }
    }


}
