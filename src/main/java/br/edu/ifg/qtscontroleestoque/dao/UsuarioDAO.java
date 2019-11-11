package br.edu.ifg.qtscontroleestoque.dao;

import br.edu.ifg.qtscontroleestoque.entity.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class UsuarioDAO extends AbstractGenericDAO {

    private final String SELECT_BY_EMAIL =  "SELECT u FROM " + Usuario.class.getSimpleName() + " u WHERE u.email = :userEmail";


    public Usuario consultarPorEmail(String email) throws NoResultException {
        Query query = entityManager.createQuery(SELECT_BY_EMAIL);
        query.setParameter("userEmail", email);
        return (Usuario) query.getSingleResult();
    }
}
