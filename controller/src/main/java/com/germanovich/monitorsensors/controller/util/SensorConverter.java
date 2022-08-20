package com.germanovich.monitorsensors.controller.util;

import com.germanovich.monitorsensors.dto.sensor.SensorDetailedDto;
import com.germanovich.monitorsensors.dto.sensor.SensorDto;
import com.germanovich.monitorsensors.dto.sensor.SensorShortDto;
import com.germanovich.monitorsensors.model.sensor.Sensor;
import com.germanovich.monitorsensors.model.type.Type;
import com.germanovich.monitorsensors.model.unit.Unit;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class SensorConverter {

    public Sensor convertSensorDtoToSensor(final SensorDto sensorDto) {
        return sensorDto == null ? null : Sensor.builder()
                .id(sensorDto.getId())
                .name(sensorDto.getName())
                .model(sensorDto.getModel())
                .range(RangeConverter.convertRangeDtoToRange(sensorDto.getRange()))
                .type(Type.builder().name(sensorDto.getType()).build())
                .unit(Unit.builder().name(sensorDto.getUnit()).build())
                .location(sensorDto.getLocation())
                .description(sensorDto.getDescription())
                .build();
    }

    public SensorDto convertSensorToSensorDto(final Sensor sensor) {
        return sensor == null ? null : SensorDto.builder()
                .id(sensor.getId())
                .name(sensor.getName())
                .model(sensor.getModel())
                .range(RangeConverter.convertRangeToRangeDto(sensor.getRange()))
                .type(sensor.getType().getName())
                .unit(sensor.getUnit().getName())
                .location(sensor.getLocation())
                .description(sensor.getDescription())
                .build();
    }

    public SensorDetailedDto convertSensorToSensorDetailedDto(final Sensor sensor) {
        return sensor == null ? null : SensorDetailedDto.builder()
                .name(sensor.getName())
                .model(sensor.getModel())
                .range(RangeConverter.convertRangeToRangeDto(sensor.getRange()))
                .type(sensor.getType().getName())
                .unit(sensor.getUnit().getName())
                .location(sensor.getLocation())
                .description(sensor.getDescription())
                .build();
    }

    public SensorShortDto convertSensorToSensorShortDto(final Sensor sensor) {
        return sensor == null ? null : SensorShortDto.builder()
                .name(sensor.getName())
                .model(sensor.getModel())
                .build();
    }

    public List<SensorShortDto> convertSensorListToSensorShortDtoList(final List<Sensor> sensorList) {
        List<SensorShortDto> sensorShortDtoList = new ArrayList<>();
        sensorList.forEach(sensor -> {
            var sensorDto = convertSensorToSensorShortDto(sensor);
            if (sensorDto != null) {
                sensorShortDtoList.add(sensorDto);
            }
        });
        return sensorShortDtoList;
    }
}
