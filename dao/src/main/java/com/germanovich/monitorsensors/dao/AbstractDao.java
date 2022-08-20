package com.germanovich.monitorsensors.dao;

import com.germanovich.monitorsensors.common.exception.DaoException;
import com.germanovich.monitorsensors.model.AEntity;
import com.germanovich.monitorsensors.model.AEntity_;
import com.hermanovich.accountingsystem.util.MessageForUser;
import lombok.extern.log4j.Log4j2;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaUpdate;
import java.io.Serializable;
import java.util.List;

@Log4j2
public abstract class AbstractDao<T extends AEntity, PK extends Serializable> implements GenericDao<T, PK> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public void save(final T entity) {
        try {
            entityManager.persist(entity);
        } catch (PersistenceException e) {
            log.error(MessageForUser.ERROR_DURING_SAVE.get(), e);
            throw new DaoException(MessageForUser.ERROR_DURING_SAVE.get());
        }
    }

    @Override
    public void update(final T entity) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaUpdate = criteriaBuilder.createCriteriaUpdate(getType());
        var root = criteriaUpdate.from(getType());

        updateSet(criteriaUpdate, entity);
        criteriaUpdate.where(criteriaBuilder.equal(root.get(AEntity_.id), entity.getId()));
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    @Override
    public void delete(final PK id) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaDelete = criteriaBuilder.createCriteriaDelete(getType());
        var root = criteriaDelete.from(getType());

        criteriaDelete.where(criteriaBuilder.equal(root.get(AEntity_.id), id));
        int number = entityManager.createQuery(criteriaDelete).executeUpdate();
        if (number == 0) {
            log.error(MessageForUser.CANNOT_DELETE.get());
            throw new DaoException(MessageForUser.CANNOT_DELETE.get());
        }
    }

    @Override
    public List<T> getAll() {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(getType());
        var root = criteriaQuery.from(getType());

        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public T getById(final PK id) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(getType());
        var root = criteriaQuery.from(getType());

        criteriaQuery.where(criteriaBuilder.equal(root.get(AEntity_.id), id));
        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    protected abstract void updateSet(final CriteriaUpdate<T> criteriaUpdate, final T entity);

    protected abstract Class<T> getType();
}
