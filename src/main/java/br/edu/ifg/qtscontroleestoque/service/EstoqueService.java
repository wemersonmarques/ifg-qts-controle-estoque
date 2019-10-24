package br.edu.ifg.qtscontroleestoque.service;

import br.edu.ifg.qtscontroleestoque.dao.MovimentacaoDAO;
import br.edu.ifg.qtscontroleestoque.dao.ProdutoDAO;
import br.edu.ifg.qtscontroleestoque.entity.Movimentacao;
import br.edu.ifg.qtscontroleestoque.entity.Produto;
import br.edu.ifg.qtscontroleestoque.type.ETipoMovimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    private MovimentacaoDAO movimentacaoDao;

    @Autowired
    private ProdutoDAO produtoDao;

    public void movimentar(ETipoMovimentacao tipoMovimentacao, Produto produto, float quantidade) {
        switch (tipoMovimentacao) {
            case ENTRADA:
                adicionarSaldoProduto(produto, quantidade);
                produtoDao.atualizar(produto);
                movimentacaoDao.salvar(new Movimentacao(tipoMovimentacao, produto, quantidade));
                break;
            case SAIDA:
                subtrairSaldoProduto(produto, quantidade);
                produtoDao.atualizar(produto);
                movimentacaoDao.salvar(new Movimentacao(tipoMovimentacao, produto, quantidade));
                break;
            default:
                break;
        }
    }

    public void adicionarSaldoProduto(Produto produto, float quantidade) {
        if (produto.getSaldoAtual() + quantidade <= produto.getEstoqueMaximo()) {
            produto.setSaldoAtual(produto.getSaldoAtual() + quantidade);
        } else {
            throw new RuntimeException("A quantidade da entrada somada ao estoque atual é maior do que o estoque máximo permitido para o produto");
        }
    }

    public void subtrairSaldoProduto(Produto produto, float quantidade) {
        if (produto.getSaldoAtual() >= quantidade && produto.getSaldoAtual() - quantidade >= produto.getEstoqueMinimo()) {
            produto.setSaldoAtual(produto.getSaldoAtual() - quantidade);
        } else {
            throw new RuntimeException("A quantidade da saida é maior que o estoque atual ou a quantidade do estoque atual menos a quantidade da saída é maior que a quantidade do estoque mínimo");
        }
    }

}
