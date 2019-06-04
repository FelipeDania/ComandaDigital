package com.example.comandadigital.dao.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.renatodasilva.exemploormlite.model.Categoria;
import com.example.renatodasilva.exemploormlite.model.Comanda;
import com.example.renatodasilva.exemploormlite.model.ItemComanda;
import com.example.renatodasilva.exemploormlite.model.Produto;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class MyORMLiteHelper extends OrmLiteSqliteOpenHelper{
    //Configuração do banco de dados
    private static final String DATABASE_NAME = "senac.db";
    private static final int DATABASE_VERSION = 2;

    public MyORMLiteHelper(Context c){
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Categoria.class);
            TableUtils.createTable(connectionSource, Produto.class);
            TableUtils.createTable(connectionSource, Comanda.class);
            TableUtils.createTable(connectionSource, ItemComanda.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Categoria.class, true);
            TableUtils.dropTable(connectionSource, Produto.class, true);
            TableUtils.dropTable(connectionSource, Comanda.class, true);
            TableUtils.dropTable(connectionSource, ItemComanda.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
