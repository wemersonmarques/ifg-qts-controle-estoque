package br.edu.ifg.qtscontroleestoque.dao;

import br.edu.ifg.qtscontroleestoque.entity.Movimentacao;
import br.edu.ifg.qtscontroleestoque.entity.Produto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class ProdutoDAO extends AbstractGenericDAO {

    private final String SELECT_BY_DESCRICAO =  "SELECT u FROM " + Produto.class.getSimpleName() + " u WHERE upper(u.descricao) = :descricao";
    private final String SELECT_MOVIMENTACOES_BY_ID =  "SELECT m FROM Movimentacao m " +
            "INNER JOIN Produto p ON m.produto = p " +
            "WHERE p.id = :id";

    public float consultarSaldo(Produto produto) {
        Produto prod = (Produto) consultarPorId(Produto.class, produto.getId());
        return prod.getSaldoAtual();
    }

    public Produto consultarPorDescricao(Produto produto) throws NoResultException {
        Query query = entityManager.createQuery(SELECT_BY_DESCRICAO).setParameter("descricao", produto.getDescricao());
        return (Produto) query.getSingleResult();
    }

    public List<Movimentacao> consultarMovimentacoes(Produto produto) {
        Query query = entityManager.createQuery(SELECT_MOVIMENTACOES_BY_ID).setParameter("id", produto.getId());
        return query.getResultList();
    }
}
