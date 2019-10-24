package br.edu.ifg.qtscontroleestoque.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public abstract class AbstractGenericDAO<T> {

    @PersistenceContext
    protected EntityManager entityManager;


    public void salvar(T t) {
        entityManager.persist(t);
    }

    public T consultarPorId(Class<T> clazz, int id) {
        return (T) entityManager.find(clazz, id);
    }

    public List<T> consultarTodos(Class clazz) {
        return (List<T>) entityManager.createQuery("SELECT u FROM " + clazz.getSimpleName() + " u").getResultList();
    }

    public void deletar(T t) {
        entityManager.remove(t);
    }

    public void atualizar(T t) {
        entityManager.merge(t);
    }

    public void deletarPorId(Class<T> clazz, int id) {
        entityManager.remove(entityManager.find(clazz, id));
    }
}