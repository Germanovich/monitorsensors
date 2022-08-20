package com.germanovich.monitorsensors.dao.user;

import com.germanovich.monitorsensors.dao.AbstractDao;
import com.germanovich.monitorsensors.model.user.User;
import com.germanovich.monitorsensors.model.user.User_;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

@Component
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

    @Override
    protected void updateSet(final CriteriaUpdate<User> criteriaUpdate, final User entity) {
        criteriaUpdate.set(User_.login, entity.getLogin());
        criteriaUpdate.set(User_.password, entity.getPassword());
        criteriaUpdate.set(User_.role, entity.getRole());
    }

    @Override
    protected Class<User> getType() {
        return User.class;
    }

    @Override
    public User getByLogin(final String login) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> model = criteriaQuery.from(User.class);
        criteriaQuery.where(criteriaBuilder.equal(model.get(User_.login), login));
        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
