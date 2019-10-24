package br.edu.ifg.qtscontroleestoque.dao;

import br.edu.ifg.qtscontroleestoque.entity.Produto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProdutoDAO extends AbstractGenericDAO {

    public float consultarSaldo(Produto produto) {
        Produto prod = (Produto) consultarPorId(Produto.class, produto.getId());
        return prod.getSaldoAtual();
    }
}
