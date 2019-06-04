package com.example.comandadigital.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.renatodasilva.exemploormlite.R;
import com.example.renatodasilva.exemploormlite.control.ComandaControl;

public class ComandaActivity extends Activity {
    private ComandaControl control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);
        control = new ComandaControl(this);
    }

    public void chamarTelaAddItem(View v){
        control.addItemComandaAction();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        control.onActivityResult(requestCode, resultCode, data);
    }
}
