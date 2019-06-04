package com.example.comandadigital.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.comandadigital.R;
import com.example.comandadigital.control.AddItemComandaControl;

public class AddItemComandaActivity extends Activity {
    private AddItemComandaControl control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_comanda);
        control = new AddItemComandaControl(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        control.configurarSpinner();
    }

    public void adicionar(View v){
        control.addItemAction();
    }

    public void voltar(View v){
        control.cancelarAction();
    }

    public void chamarTelaProdutos(View v){
        control.chamarTelaProdutoAction();
    }
}
