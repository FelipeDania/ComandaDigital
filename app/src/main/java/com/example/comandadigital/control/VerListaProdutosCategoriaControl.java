package com.example.comandadigital.control;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.comandadigital.R;
import com.example.comandadigital.model.Categoria;
import com.example.comandadigital.model.Produto;


public class VerListaProdutosCategoriaControl {
    private Activity activity;
    private ListView lvProdutos;
    private ArrayAdapter<Produto> adapterProdutos;
    private Categoria categoria;

    public VerListaProdutosCategoriaControl(Activity activity) {
        this.activity = activity;
        categoria = (Categoria) activity.getIntent().getSerializableExtra("categoria");

        initComponents();
        carregarListView();
    }

    private void initComponents(){
        lvProdutos = activity.findViewById(R.id.lvListaProdutos);
    }

    private void carregarListView(){
        adapterProdutos = new ArrayAdapter<Produto>(
                activity,
                android.R.layout.simple_list_item_1,
                categoria.getListaProduto()
        );
        lvProdutos.setAdapter(adapterProdutos);
    }

}
