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
                produto.setSaldoAtual(produto.getSaldoAtual() + quantidade);
                produtoDao.atualizar(produto);
                movimentacaoDao.salvar(new Movimentacao(tipoMovimentacao, produto, quantidade));
                break;
            case SAIDA:
                if (quantidade > produtoDao.consultarSaldo(produto)) {
                    throw new RuntimeException("O saldo do produto é superior à quantidade da saída");
                } else {
                    produto.setSaldoAtual(produto.getSaldoAtual() - quantidade);
                    produtoDao.atualizar(produto);
                    movimentacaoDao.salvar(new Movimentacao(tipoMovimentacao, produto, quantidade));
                }
                break;
            default:
                break;
        }

    }

    public float consultarSaldo(Produto produto) {
        return produtoDao.consultarSaldo(produto);
    }

}
