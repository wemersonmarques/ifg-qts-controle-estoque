package br.edu.ifg.qtscontroleestoque.entity;

import br.edu.ifg.qtscontroleestoque.type.ETipoMovimentacao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movimentacoes")
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private ETipoMovimentacao tipoMovimentacao;
    @Column
    private float quantidade;
    @OneToOne
    private Produto produto;
    @CreationTimestamp
    private Date dataMovimentacao;

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Movimentacao(ETipoMovimentacao tipoMovimentacao, Produto produto, float quantidade) {
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public Movimentacao() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ETipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(ETipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }
}
