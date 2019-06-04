package com.example.comandadigital.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.renatodasilva.exemploormlite.R;
import com.example.renatodasilva.exemploormlite.dao.CategoriaDao;
import com.example.renatodasilva.exemploormlite.model.Categoria;
import com.example.renatodasilva.exemploormlite.model.Produto;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class  CategoriaControl {
    private Activity activity;
    private CategoriaDao categoriaDao;
    private EditText editNome;
    private ListView lvCategorias;
    private ArrayAdapter<Categoria> adapterCategoria;
    private Categoria categoria = null;

    public CategoriaControl(Activity activity) {
        this.activity = activity;
        categoriaDao = new CategoriaDao(this.activity);

        initComponents();
        configurarListView();
    }

    private void initComponents(){
        editNome = activity.findViewById(R.id.editNomeCategoria);
        lvCategorias = activity.findViewById(R.id.lvCategorias);
    }

    private void configurarListView(){
        cliqueCurto();
        cliqueLongo();
        carregarListView();
    }

    private void cliqueCurto(){
        lvCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                categoria = adapterCategoria.getItem(position);
                AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Monstrando categoria");
                alerta.setMessage(categoria.toString());
                alerta.setIcon(android.R.drawable.ic_menu_view);
                alerta.setNeutralButton("Fechar", null);
                alerta.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editNome.setText(categoria.getNome());
                    }
                });
                alerta.setNegativeButton("Ver produtos", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Primeira maneira de mostrar os produtos em um Dialog
                        ArrayAdapter<Produto> adapter = new ArrayAdapter<>(
                                activity,
                                android.R.layout.simple_list_item_1,
                                categoria.getListaProduto()
                        );
                        AlertDialog.Builder dlg = new AlertDialog.Builder(activity);
                        dlg.setAdapter(adapter, null);
                        dlg.show();

                        //Segunda maneira de mostrar os produtos em um Dialog
                        /*Intent it = new Intent(activity, VerListaProdutoCategoriaActivity.class);
                        it.putExtra("categoria", categoria);
                        activity.startActivity(it);*/
                    }
                });
                alerta.show();
            }
        });
    }

    private void cliqueLongo(){
        lvCategorias.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                categoria = adapterCategoria.getItem(position);
                AlertDialog.Builder alerta;
                alerta = new AlertDialog.Builder(activity);
                alerta.setTitle("Excluindo categoria");
                alerta.setMessage("Deseja realmente excluir a " +
                        "categoria " +categoria.getNome()+"?");
                alerta.setIcon(android.R.drawable.ic_menu_delete);
                alerta.setNeutralButton("Fechar", null);
                alerta.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            categoriaDao.getDao().delete(categoria);
                            adapterCategoria.remove(categoria);
                            categoria = null;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                alerta.show();
                return false;
            }
        });


    }

    private void carregarListView() {
        try {
            adapterCategoria = new ArrayAdapter<>(
                    activity,
                    android.R.layout.simple_list_item_1,
                    categoriaDao.getDao().queryForAll()
            );
            lvCategorias.setAdapter(adapterCategoria);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvarAction(){
        if(categoria == null){
            categoria = new Categoria();
        }
        categoria.setNome(editNome.getText().toString());

        try {
            Dao.CreateOrUpdateStatus res = categoriaDao.getDao().createOrUpdate(categoria);
            if(res.isCreated()){
                Toast.makeText(activity, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                adapterCategoria.add(categoria);
            } else if(res.isUpdated()){
                Toast.makeText(activity, "Editado com sucesso", Toast.LENGTH_SHORT).show();
                adapterCategoria.notifyDataSetChanged();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        categoria = null;
    }

    private boolean validarCategoria(Categoria c){
        if(c.getNome().isEmpty()){
            Toast.makeText(activity, "Nome é obrigatório", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}
