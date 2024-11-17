package br.com.fiap.fiapstore.model;

import java.time.LocalDate;

public class Produto {

    private int codigo;
    private String nome;
    private double descricao;
    private int quantidade;
    private double valor;
    private LocalDate dataFabricacao;

    public Produto() {
    }

    public Produto(int codigo, String nome, double descricao, int quantidade, double valor, LocalDate dataFabricacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataFabricacao = dataFabricacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getDescricao() {
        return descricao;
    }

    public void setDescricao(double descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }
}
