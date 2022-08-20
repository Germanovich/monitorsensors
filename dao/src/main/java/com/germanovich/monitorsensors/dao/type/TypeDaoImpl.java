package com.germanovich.monitorsensors.dao.type;

import com.germanovich.monitorsensors.dao.AbstractDao;
import com.germanovich.monitorsensors.model.type.Type;
import com.germanovich.monitorsensors.model.type.Type_;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaUpdate;

@Component
public class TypeDaoImpl extends AbstractDao<Type, Long> implements TypeDao {

    @Override
    protected void updateSet(CriteriaUpdate<Type> criteriaUpdate, Type entity) {
        criteriaUpdate.set(Type_.name, entity.getName());
    }

    @Override
    protected Class<Type> getType() {
        return Type.class;
    }
}
