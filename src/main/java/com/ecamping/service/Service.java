/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.service;

import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.TRANSACTION;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author laura
 * @param <T>
 */
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED)
public abstract class Service<T> {

    @PersistenceContext(name = "ecampingPersistence", type = TRANSACTION)
    protected EntityManager entityManager;
    protected Class<T> classe;

    @TransactionAttribute(NOT_SUPPORTED)
    protected void setClasse(@NotNull Class<T> classe) {
        this.classe = classe;
    }

    @TransactionAttribute(SUPPORTS)
    public abstract T create();

    @TransactionAttribute(SUPPORTS)
    public boolean exist(@NotNull T entidade) {
        return true;
    }

    public void persistence(@Valid T entidade) {
        if (!exist(entidade)) {
            entityManager.persist(entidade);
        }
    }

    public void update(@Valid T entidade) {
        if (exist(entidade)) {
            entityManager.merge(entidade);
            entityManager.flush();
        }
    }


    public void delete(@Valid T entidade) {
        if (exist(entidade)) {
            entidade = entityManager.merge(entidade);
            entityManager.remove(entidade);
            entityManager.flush();
        }
    }

    @TransactionAttribute(SUPPORTS)
    public T findId(@NotNull Long id) {
        return entityManager.find(classe, id);
    }

    @TransactionAttribute(SUPPORTS)
    protected T findEntity(Object[] parametros, String nomeQuery) {
        TypedQuery<T> query = entityManager.createNamedQuery(nomeQuery, classe);

        int i = 1;
        for (Object parametro : parametros) {
            query.setParameter(i++, parametro);
        }

        return (T) query.getSingleResult();
    }

    @TransactionAttribute(SUPPORTS)
    protected T findEntity(String nomeQuery) {
        TypedQuery<T> query = entityManager.createNamedQuery(nomeQuery, classe);
        return query.getSingleResult();
    }

    @TransactionAttribute(SUPPORTS)
    protected List<T> findEntities(Object[] parametros, String nomeQuery) {
        TypedQuery<T> query = entityManager.createNamedQuery(nomeQuery, classe);

        int i = 1;
        for (Object parametro : parametros) {
            query.setParameter(i++, parametro);
        }

        return query.getResultList();
    }

    @TransactionAttribute(SUPPORTS)
    protected List<T> findEntities(String nomeQuery) {
        TypedQuery<T> query = entityManager.createNamedQuery(nomeQuery, classe);
        return query.getResultList();
    }

    @TransactionAttribute(SUPPORTS)
    protected List<T> findEntitiesJQL(Object[] parametros, String nomeQuery) {
        TypedQuery<T> query = entityManager.createQuery(nomeQuery, classe);
        int i = 1;
        for (Object parametro : parametros) {
            query.setParameter(i++, parametro);
        }
        return query.getResultList();
    }

    @TransactionAttribute(SUPPORTS)
    protected List<T> findEntitiesJQL(String nomeQuery) {
        TypedQuery<T> query = entityManager.createQuery(nomeQuery, classe);

        return query.getResultList();
    }

    @TransactionAttribute(SUPPORTS)
    protected T findEntityJQL(Object[] parametros, String nomeQuery) {
        Query query = entityManager.createQuery(nomeQuery, classe);
        int i = 1;
        for (Object parametro : parametros) {
            query.setParameter(i++, parametro);
        }
        return (T) query.getSingleResult();
    }

    @TransactionAttribute(SUPPORTS)
    protected long findQuantidade(Object[] parametros, String nomeQuery) {
        Query query = entityManager.createQuery(nomeQuery, classe);
        int i = 1;
        for (Object parametro : parametros) {
            query.setParameter(i++, parametro);
        }
        return (long) query.getSingleResult();
    }

}
