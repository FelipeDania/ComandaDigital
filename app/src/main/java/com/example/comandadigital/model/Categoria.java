package com.example.comandadigital.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@DatabaseTable(tableName = "categoria")
public class Categoria implements Serializable{
    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;
    @DatabaseField(columnName = "nome", canBeNull = false, width = 60)
    private String nome;
    @ForeignCollectionField(eager = true)
    private Collection<Produto> colecaoProdutos;

    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getListaProduto(){
        List<Produto> lista = new ArrayList<>();
        lista.addAll(colecaoProdutos);
        return lista;
    }

    public Collection<Produto> getColecaoProdutos() {
        return colecaoProdutos;
    }

    public void setColecaoProdutos(Collection<Produto> colecaoProdutos) {
        this.colecaoProdutos = colecaoProdutos;
    }

    @Override
    public String toString() {
       try {
           return id + " - " + nome + " (" + colecaoProdutos.size() + ")";
       } catch (Exception e){
           return id + " - " + nome + " (0)";
       }
    }
}
