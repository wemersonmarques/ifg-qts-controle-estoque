package br.edu.ifg.qtscontroleestoque.dto;

import br.edu.ifg.qtscontroleestoque.entity.Produto;

public class ProdutoDTO {
    private int id;
    private String descricao;
    private float saldoAtual;
    private float estoqueMaximo;
    private float estoqueMinimo;
    private String mensagemErro;

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.descricao = produto.getDescricao();
        this.saldoAtual = produto.getSaldoAtual();
        this.estoqueMaximo = produto.getEstoqueMaximo();
        this.estoqueMinimo = produto.getEstoqueMinimo();
    }
    public ProdutoDTO() {

    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(float saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public float getEstoqueMaximo() {
        return estoqueMaximo;
    }

    public void setEstoqueMaximo(float estoqueMaximo) {
        this.estoqueMaximo = estoqueMaximo;
    }

    public float getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(float estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }
}
