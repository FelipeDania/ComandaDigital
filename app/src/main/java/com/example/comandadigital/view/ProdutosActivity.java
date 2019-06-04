package com.example.comandadigital.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.renatodasilva.exemploormlite.R;
import com.example.renatodasilva.exemploormlite.control.ProdutosControl;

public class ProdutosActivity extends Activity {

    private ProdutosControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        control = new ProdutosControl(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        control.montarSpinnerAction();
    }

    public void chamarTelaCategoria(View v){
        control.chamarCategoriaAction();
    }

    public void salvar(View v){
        control.salvarAction();
    }
}
