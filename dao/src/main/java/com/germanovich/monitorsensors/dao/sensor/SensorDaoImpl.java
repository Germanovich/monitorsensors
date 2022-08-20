package com.germanovich.monitorsensors.dao.sensor;

import com.germanovich.monitorsensors.dao.AbstractDao;
import com.germanovich.monitorsensors.model.sensor.Sensor;
import com.germanovich.monitorsensors.model.sensor.Sensor_;
import com.germanovich.monitorsensors.model.unit.Unit;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaUpdate;
import java.util.List;

@Component
public class SensorDaoImpl extends AbstractDao<Sensor, Long> implements SensorDao {

    @Override
    protected void updateSet(CriteriaUpdate<Sensor> criteriaUpdate, Sensor entity) {
        criteriaUpdate.set(Sensor_.RANGE, entity.getRange().getId());
        criteriaUpdate.set(Sensor_.TYPE, entity.getType().getId());
        criteriaUpdate.set(Sensor_.name, entity.getName());
        criteriaUpdate.set(Sensor_.model, entity.getModel());

        if (entity.getUnit() == null) {
            criteriaUpdate.set(Sensor_.UNIT, (Unit) null);
        } else {
            criteriaUpdate.set(Sensor_.UNIT, entity.getUnit().getId());
        }
        if (entity.getLocation() == null) {
            criteriaUpdate.set(Sensor_.location, (String) null);
        } else {
            criteriaUpdate.set(Sensor_.location, entity.getLocation());
        }
        if (entity.getDescription() == null) {
            criteriaUpdate.set(Sensor_.description, (String) null);
        } else {
            criteriaUpdate.set(Sensor_.description, entity.getDescription());
        }
    }

    @Override
    protected Class<Sensor> getType() {
        return Sensor.class;
    }

    @Override
    public List<Sensor> findByNameOrModelOrDescriptionContains(final String world) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Sensor.class);
        var root = criteriaQuery.from(Sensor.class);
        var pattern = "%" + world + "%";

        var predicateByName = criteriaBuilder.like(root.get(Sensor_.name), pattern);
        var predicateByModel = criteriaBuilder.like(root.get(Sensor_.model), pattern);
        var predicateByDescription = criteriaBuilder.like(root.get(Sensor_.description), pattern);

        var finalPredicate = criteriaBuilder.or(predicateByName, predicateByModel, predicateByDescription);
        criteriaQuery.where(finalPredicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
