package com.example.comandadigital.dao;

import android.content.Context;

import com.example.comandadigital.dao.helpers.DaoHelper;
import com.example.comandadigital.model.ItemComanda;

public class ItemComandaDao extends DaoHelper<ItemComanda> {
    public ItemComandaDao(Context c) {
        super(c, ItemComanda.class);
    }
}
