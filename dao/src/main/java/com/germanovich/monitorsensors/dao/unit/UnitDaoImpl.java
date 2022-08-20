package com.germanovich.monitorsensors.dao.unit;

import com.germanovich.monitorsensors.dao.AbstractDao;
import com.germanovich.monitorsensors.model.unit.Unit;
import com.germanovich.monitorsensors.model.unit.Unit_;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaUpdate;

@Component
public class UnitDaoImpl extends AbstractDao<Unit, Long> implements UnitDao {
    @Override
    protected void updateSet(CriteriaUpdate<Unit> criteriaUpdate, Unit entity) {
        criteriaUpdate.set(Unit_.name, entity.getName());
    }

    @Override
    protected Class<Unit> getType() {
        return Unit.class;
    }
}
