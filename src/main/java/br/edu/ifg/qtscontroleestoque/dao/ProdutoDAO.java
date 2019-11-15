package br.edu.ifg.qtscontroleestoque.dao;

import br.edu.ifg.qtscontroleestoque.entity.Produto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
@Transactional
public class ProdutoDAO extends AbstractGenericDAO {

    private final String SELECT_BY_DESCRICAO =  "SELECT u FROM " + Produto.class.getSimpleName() + " u WHERE upper(u.descricao) = :descricao";

    public float consultarSaldo(Produto produto) {
        Produto prod = (Produto) consultarPorId(Produto.class, produto.getId());
        return prod.getSaldoAtual();
    }

    public Produto consultarPorDescricao(Produto produto) throws NoResultException {
        Query query = entityManager.createQuery(SELECT_BY_DESCRICAO);
        query.setParameter("descricao", produto.getDescricao());
        return (Produto) query.getSingleResult();
    }
}
