package com.example.comandadigital.dao;

import android.content.Context;

import com.example.renatodasilva.exemploormlite.dao.helpers.DaoHelper;
import com.example.renatodasilva.exemploormlite.model.Produto;

public class ProdutoDao extends DaoHelper<Produto>{
    public ProdutoDao(Context c) {
        super(c, Produto.class);
    }
}
