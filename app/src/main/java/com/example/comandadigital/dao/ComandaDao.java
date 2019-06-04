package com.example.comandadigital.dao;

import android.content.Context;

import com.example.renatodasilva.exemploormlite.dao.helpers.DaoHelper;
import com.example.renatodasilva.exemploormlite.model.Comanda;

public class ComandaDao extends DaoHelper<Comanda>{
    public ComandaDao(Context c) {
        super(c, Comanda.class);
    }
}
