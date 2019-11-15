package br.edu.ifg.qtscontroleestoque.entity;

import br.edu.ifg.qtscontroleestoque.dto.ProdutoDTO;

import javax.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String descricao;

    @Column
    private float saldoAtual;

    @Column
    private float estoqueMaximo;

    @Column
    private float estoqueMinimo;

    public Produto() {}

    public Produto(ProdutoDTO produtoDTO) {
        this.id = produtoDTO.getId();
        this.descricao = produtoDTO.getDescricao();
        this.saldoAtual = produtoDTO.getSaldoAtual();
        this.estoqueMaximo = produtoDTO.getEstoqueMaximo();
        this.estoqueMinimo = produtoDTO.getEstoqueMinimo();
    }

    public float getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(float saldoAtual) {
        this.saldoAtual = saldoAtual;
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

