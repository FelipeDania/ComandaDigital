package com.example.comandadigital.control;

import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.example.renatodasilva.exemploormlite.R;
import com.example.renatodasilva.exemploormlite.dao.ProdutoDao;
import com.example.renatodasilva.exemploormlite.model.ItemComanda;
import com.example.renatodasilva.exemploormlite.model.Produto;
import com.example.renatodasilva.exemploormlite.view.ProdutosActivity;

import java.sql.SQLException;

public class AddItemComandaControl {
    private Activity activity;
    private Spinner spProdutos;
    private NumberPicker npQuantidade;

    private ArrayAdapter<Produto> adapterProdutos;
    private ProdutoDao produtoDao;
    private ItemComanda itemComanda;

    public AddItemComandaControl(Activity activity) {
        this.activity = activity;
        produtoDao = new ProdutoDao(activity);
        initComponents();
    }

    private void initComponents(){
        spProdutos = activity.findViewById(R.id.spProdutos);
        npQuantidade = activity.findViewById(R.id.npQuantidade);
        configurarNumberPicker();
        configurarSpinner();
    }

    private void configurarNumberPicker(){
        npQuantidade.setMinValue(1);
        npQuantidade.setMaxValue(99);
    }

    public void configurarSpinner(){
        try {
            adapterProdutos = new ArrayAdapter<>(
                    activity,
                    android.R.layout.simple_spinner_item,
                    produtoDao.getDao().queryForAll()
            );
            spProdutos.setAdapter(adapterProdutos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItemAction(){
        itemComanda = new ItemComanda();
        itemComanda.setProduto((Produto) spProdutos.getSelectedItem());
        itemComanda.setQuantidade(npQuantidade.getValue());

        Intent it = new Intent();
        it.putExtra("pitem", itemComanda);
        activity.setResult(activity.RESULT_OK, it);
        activity.finish();
    }

    public void cancelarAction(){
        activity.setResult(activity.RESULT_CANCELED);
        activity.finish();
    }

    public void chamarTelaProdutoAction(){
        Intent it = new Intent(activity, ProdutosActivity.class);
        activity.startActivity(it);
    }

}
