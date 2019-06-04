package com.example.comandadigital.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.renatodasilva.exemploormlite.R;
import com.example.renatodasilva.exemploormlite.dao.ComandaDao;
import com.example.renatodasilva.exemploormlite.model.Comanda;
import com.example.renatodasilva.exemploormlite.view.ComandaActivity;

import java.sql.SQLException;

public class MinhasComandasControl {
    private Activity activity;
    private EditText editNumeroMesa;
    private ListView lvComandas;
    private ArrayAdapter<Comanda> adapterComanda;
    private ComandaDao comandaDao;
    private Comanda comanda;

    public MinhasComandasControl(Activity activity) {
        this.activity = activity;
        comandaDao = new ComandaDao(activity);
        initComponents();
    }

    private void initComponents(){
        editNumeroMesa = activity.findViewById(R.id.editNumeroMesa);
        lvComandas = activity.findViewById(R.id.lvComandas);
        configurarListView();
    }

    private void configurarListView(){
        try {
            adapterComanda = new ArrayAdapter<>(
                activity,
                android.R.layout.simple_list_item_1,
                comandaDao.getDao().queryForAll()
            );
            lvComandas.setAdapter(adapterComanda);
            cliqueCurto();
            cliqueLongo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cliqueCurto(){
        lvComandas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                comanda = adapterComanda.getItem(position);
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Comanda " + comanda.getId());
                alerta.setMessage(comanda.toString());
                alerta.setNeutralButton("Fechar", null);
                alerta.setPositiveButton("Ver itens", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent it = new Intent(activity, ComandaActivity.class);
                        it.putExtra("pcomanda", comanda);
                        activity.startActivity(it);
                    }
                });
                alerta.show();
            }
        });
    }

    private void cliqueLongo(){
        //TODO Desenvolver a exclusão com clique longo
    }

    public void novaComandaAction(){
        comanda = new Comanda();
        comanda.setMesa(Integer.parseInt(editNumeroMesa.getText().toString()));
        try {
            comandaDao.getDao().create(comanda);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Intent it = new Intent(activity, ComandaActivity.class);
        it.putExtra("pcomanda", comanda);
        activity.startActivity(it);
    }
}
