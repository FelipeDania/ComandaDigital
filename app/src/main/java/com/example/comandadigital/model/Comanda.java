package com.example.comandadigital.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@DatabaseTable(tableName = "comanda")
public class Comanda implements Serializable {
    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;
    @DatabaseField(canBeNull = false)
    private String local;
    @DatabaseField(canBeNull = false)
    private Integer mesa;
    @ForeignCollectionField(eager = true)
    private Collection<ItemComanda> colecaoItens;

    public Comanda() {
    }

    public Comanda(String local, Integer mesa) {
        this.local = local;
        this.mesa = mesa;
    }

    public Comanda(Integer id, String local, Integer mesa) {
        this.id = id;
        this.local = local;
        this.mesa = mesa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public Collection<ItemComanda> getColecaoItens() {
        return colecaoItens;
    }

    public void setColecaoItens(Collection<ItemComanda> colecaoItens) {
        this.colecaoItens = colecaoItens;
    }

    public List<ItemComanda> getListaItensComanda(){
        try {
            return new ArrayList<>(this.colecaoItens);
        } catch(Exception e){
            return new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        return id + " Mesa: " + mesa+"\n" +
                "Local: "+local + " Itens " + getListaItensComanda().size();
    }
}
