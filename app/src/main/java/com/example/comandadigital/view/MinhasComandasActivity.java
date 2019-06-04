package com.example.comandadigital.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.renatodasilva.exemploormlite.R;
import com.example.renatodasilva.exemploormlite.control.MinhasComandasControl;

public class MinhasComandasActivity extends Activity {

    private MinhasComandasControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_comandas);
        control = new MinhasComandasControl(this);
    }

    public void novaComanda(View v){
        control.novaComandaAction();
    }

}
