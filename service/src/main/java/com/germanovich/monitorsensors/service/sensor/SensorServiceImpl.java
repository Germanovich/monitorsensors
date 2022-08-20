package com.germanovich.monitorsensors.service.sensor;

import com.germanovich.monitorsensors.common.exception.BusinessException;
import com.germanovich.monitorsensors.dao.range.RangeDao;
import com.germanovich.monitorsensors.dao.sensor.SensorDao;
import com.germanovich.monitorsensors.dao.type.TypeDao;
import com.germanovich.monitorsensors.dao.unit.UnitDao;
import com.germanovich.monitorsensors.model.sensor.Sensor;
import com.germanovich.monitorsensors.model.type.Type;
import com.germanovich.monitorsensors.model.unit.Unit;
import com.hermanovich.accountingsystem.util.MessageForUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final SensorDao sensorDao;
    private final TypeDao typeDao;
    private final UnitDao unitDao;
    private final RangeDao rangeDao;

    @Transactional
    @Override
    public void addSensor(final Sensor sensor) {
        validateSensorData(sensor);

        typeDao.getAll().forEach(type -> {
            if (type.getName().equals(sensor.getType().getName())) {
                sensor.setType(type);
            }
        });
        if (sensor.getUnit() != null) {
            unitDao.getAll().forEach(unit -> {
                if (unit.getName().equals(sensor.getUnit().getName())) {
                    sensor.setUnit(unit);
                }
            });
        }

        sensorDao.save(sensor);
    }

    @Transactional
    @Override
    public void removeSensor(final long id) {
        rangeDao.delete(id);
    }

    @Transactional
    @Override
    public void updateSensor(final Sensor sensor) {
        validateSensorData(sensor);

        if (sensor.getId() < 1) {
            throw new BusinessException(MessageForUser.WRONG_ID.get());
        }

        typeDao.getAll().forEach(type -> {
            if (type.getName().equals(sensor.getType().getName())) {
                sensor.getType().setId(type.getId());
            }
        });
        sensor.getRange().setId(sensor.getId());
        if (sensor.getUnit() != null) {
            unitDao.getAll().forEach(unit -> {
                if (unit.getName().equals(sensor.getUnit().getName())) {
                    sensor.getUnit().setId(unit.getId());
                }
            });
        }

        sensorDao.update(sensor);
    }

    @Override
    public Sensor getSensor(final long id) {
        return sensorDao.getById(id);
    }

    @Override
    public List<Sensor> getSensors() {
        return sensorDao.getAll();
    }

    @Override
    public List<Sensor> getSensorsByWord(final String word) {
        if (word == null) {
            throw new BusinessException(MessageForUser.WORD_IS_MANDATORY.get());
        }
        return sensorDao.findByNameOrModelOrDescriptionContains(word);
    }

    private void validateSensorData(final Sensor sensor) {
        boolean typeCorrect = typeDao.getAll().stream()
                .map(Type::getName)
                .collect(Collectors.toList())
                .contains(sensor.getType().getName());
        boolean unitCorrect = sensor.getUnit() == null || unitDao.getAll().stream()
                .map(Unit::getName)
                .collect(Collectors.toList())
                .contains(sensor.getUnit().getName());
        if (!typeCorrect || !unitCorrect) {
            throw new BusinessException(MessageForUser.NOT_FOUND_ALL_CORRECT_DATA.get());
        }
    }
}
