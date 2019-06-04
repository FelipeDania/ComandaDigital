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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.renatodasilva.exemploormlite.R;
import com.example.renatodasilva.exemploormlite.dao.CategoriaDao;
import com.example.renatodasilva.exemploormlite.dao.ProdutoDao;
import com.example.renatodasilva.exemploormlite.model.Categoria;
import com.example.renatodasilva.exemploormlite.model.Produto;
import com.example.renatodasilva.exemploormlite.view.CategoriasActivity;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class ProdutosControl {
    private Activity activity;
    private EditText editNome;
    private EditText editValor;
    private ListView lvProdutos;
    private Spinner spCategorias;

    private ArrayAdapter<Produto> adapterProdutos;
    private ArrayAdapter<Categoria> adapterCategorias;

    private Produto produto;
    private CategoriaDao categoriaDao;
    private ProdutoDao produtoDao;

    public ProdutosControl(Activity activity) {
        this.activity = activity;
        categoriaDao = new CategoriaDao(this.activity);
        produtoDao = new ProdutoDao(this.activity);
        initComponents();
        montarListView();
    }

    public void montarSpinnerAction(){
        try {
            adapterCategorias = new ArrayAdapter<>(
                    activity,
                    android.R.layout.simple_spinner_dropdown_item,
                    categoriaDao.getDao().queryForAll()
            );
            spCategorias.setAdapter(adapterCategorias);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void montarListView(){
        try {
            adapterProdutos = new ArrayAdapter<>(
                    activity,
                    android.R.layout.simple_list_item_1,
                    produtoDao.getDao().queryForAll()
            );
            lvProdutos.setAdapter(adapterProdutos);
            cliqueCurto();
            cliqueLongo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void chamarCategoriaAction(){
        Intent it = new Intent(activity, CategoriasActivity.class);
        activity.startActivity(it);
    }

    private void initComponents(){
        editNome = activity.findViewById(R.id.editNomeProduto);
        editValor = activity.findViewById(R.id.editValorProduto);
        spCategorias = activity.findViewById(R.id.spCategorias);
        lvProdutos = activity.findViewById(R.id.lvProdutos);
    }

    /* Opção para editar e fechar */
    private void cliqueCurto(){
        lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                produto = adapterProdutos.getItem(position);
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Visualizando produto");
                alerta.setMessage(produto.toString());
                alerta.setNeutralButton("Fechar", null);
                alerta.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editNome.setText(produto.getNome());
                        editValor.setText(String.valueOf(produto.getValor()));

                        //Selecionar a categoria do produto no spinner
                        for(int i=0; i<adapterCategorias.getCount(); i++){
                            Categoria c = adapterCategorias.getItem(i);
                            if(produto.getCategoria().getId() == c.getId()){
                                spCategorias.setSelection(i);
                                break;
                            }
                        }
                    }
                });
                alerta.show();
            }
        });
    }

    //Opção para excluir e fechar
    private void cliqueLongo(){
        lvProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                produto = adapterProdutos.getItem(position);
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Excluindo produto");
                alerta.setMessage("Deseja realmente excluir o produto " + produto.getNome()+"?");
                alerta.setIcon(android.R.drawable.ic_menu_delete);
                alerta.setNeutralButton("Não", null);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            produtoDao.getDao().delete(produto);
                            adapterProdutos.remove(produto);
                            produto = null;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                alerta.show();
                //VER A DIFERENÇA SE FOR RETURN TRUE
                return true;
            }
        });
    }

    public void salvarAction(){
        if(produto == null){
            produto = new Produto();
        }

        produto.setNome(editNome.getText().toString());
        produto.setValor(editValor.getText().toString());
        produto.setCategoria((Categoria) spCategorias.getSelectedItem());

        try {
            Dao.CreateOrUpdateStatus res = produtoDao.getDao().createOrUpdate(produto);
            if(res.isCreated()){
                adapterProdutos.add(produto);
                Toast.makeText(activity, "Cadastrado", Toast.LENGTH_SHORT).show();
            } else if(res.isUpdated()){
                adapterProdutos.notifyDataSetChanged();
            }
            produto = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
