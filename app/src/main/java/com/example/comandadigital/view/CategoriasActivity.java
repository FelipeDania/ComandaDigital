package com.example.comandadigital.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.renatodasilva.exemploormlite.R;
import com.example.renatodasilva.exemploormlite.control.CategoriaControl;

public class CategoriasActivity extends Activity {

    CategoriaControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        control = new CategoriaControl(this);
    }

    public void salvar(View v){
        control.salvarAction();
    }
}