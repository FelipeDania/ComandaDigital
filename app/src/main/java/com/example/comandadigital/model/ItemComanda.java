package com.example.comandadigital.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "item_comanda")
public class ItemComanda implements Serializable {
    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;
    @DatabaseField(foreign = true, foreignAutoCreate = false)
    private Comanda comanda;
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Produto produto;
    @DatabaseField(canBeNull = false)
    private Double valor;
    @DatabaseField(canBeNull = false, defaultValue = "1")
    private Integer quantidade;

    public ItemComanda() {
    }

    public ItemComanda(Comanda comanda, Produto produto, Double valor, Integer quantidade) {
        this.comanda = comanda;
        this.produto = produto;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public ItemComanda(Integer id, Comanda comanda, Produto produto, Double valor, Integer quantidade) {
        this.id = id;
        this.comanda = comanda;
        this.produto = produto;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.valor = produto.getValor();
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getSubtotal(){
        return quantidade*valor;
    }

    @Override
    public String toString() {
        return produto.getNome() + " - R$" + valor + " - Qtd.: "+quantidade+"\nR$"+getSubtotal();
    }
}
