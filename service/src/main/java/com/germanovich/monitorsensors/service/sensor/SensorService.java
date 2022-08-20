package com.germanovich.monitorsensors.service.sensor;

import com.germanovich.monitorsensors.model.sensor.Sensor;

import java.util.List;

public interface SensorService {

    void addSensor(final Sensor sensor);

    void removeSensor(final long id);

    void updateSensor(final Sensor sensor);

    Sensor getSensor(final long id);

    List<Sensor> getSensors();

    List<Sensor> getSensorsByWord(final String word);
}
