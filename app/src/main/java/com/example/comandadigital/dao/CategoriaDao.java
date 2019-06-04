package com.example.comandadigital.dao;

import android.content.Context;

import com.example.renatodasilva.exemploormlite.dao.helpers.DaoHelper;
import com.example.renatodasilva.exemploormlite.model.Categoria;

public class CategoriaDao extends DaoHelper<Categoria> {

    public CategoriaDao(Context c) {
        super(c, Categoria.class);
    }

}
