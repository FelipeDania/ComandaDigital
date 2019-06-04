package com.example.comandadigital.view;

import android.app.Activity;
import android.os.Bundle;

import com.example.renatodasilva.exemploormlite.R;
import com.example.renatodasilva.exemploormlite.control.VerListaProdutosCategoriaControl;

public class VerListaProdutoCategoriaActivity extends Activity {
    private VerListaProdutosCategoriaControl control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_lista_produto_categoria);
        control = new VerListaProdutosCategoriaControl(this);
    }
}
