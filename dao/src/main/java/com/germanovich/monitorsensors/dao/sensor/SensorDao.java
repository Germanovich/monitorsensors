package com.germanovich.monitorsensors.dao.sensor;

import com.germanovich.monitorsensors.dao.GenericDao;
import com.germanovich.monitorsensors.model.sensor.Sensor;

import java.util.List;

public interface SensorDao extends GenericDao<Sensor, Long> {

    List<Sensor> findByNameOrModelOrDescriptionContains(final String world);
}
