package com.gemanovich.monitorsensors.service.sensor;

import com.germanovich.monitorsensors.dao.range.RangeDao;
import com.germanovich.monitorsensors.dao.sensor.SensorDao;
import com.germanovich.monitorsensors.dao.type.TypeDao;
import com.germanovich.monitorsensors.dao.unit.UnitDao;
import com.germanovich.monitorsensors.model.range.Range;
import com.germanovich.monitorsensors.model.sensor.Sensor;
import com.germanovich.monitorsensors.model.type.Type;
import com.germanovich.monitorsensors.model.unit.Unit;
import com.germanovich.monitorsensors.service.sensor.SensorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SensorServiceImplTest {

    @InjectMocks
    SensorServiceImpl sensorService;
    @Mock
    SensorDao sensorDao;
    @Mock
    TypeDao typeDao;
    @Mock
    UnitDao unitDao;
    @Mock
    RangeDao rangeDao;

    @Test
    void SensorServiceImplTest_addSensor() {
        Type type = Type.builder().name("type").build();
        Unit unit = Unit.builder().name("unit").build();
        Range range = Range.builder().id(1).from(11).to(44).build();
        Sensor sensor = Sensor.builder()
                .id(1)
                .name("name")
                .model("model")
                .range(range)
                .type(type)
                .unit(unit)
                .location("loc")
                .description("desc")
                .build();

        Mockito.doReturn(List.of(unit)).when(unitDao).getAll();
        Mockito.doReturn(List.of(type)).when(typeDao).getAll();


        sensorService.addSensor(sensor);

        Mockito.verify(sensorDao).save(sensor);
    }

    @Test
    void SensorServiceImplTest_updateSensor() {
        Type type = Type.builder().name("type").build();
        Unit unit = Unit.builder().name("unit").build();
        Range range = Range.builder().id(1).from(11).to(44).build();
        Sensor sensor = Sensor.builder()
                .id(1)
                .name("name")
                .model("model")
                .range(range)
                .type(type)
                .unit(unit)
                .location("loc")
                .description("desc")
                .build();

        Mockito.doReturn(List.of(unit)).when(unitDao).getAll();
        Mockito.doReturn(List.of(type)).when(typeDao).getAll();

        sensorService.updateSensor(sensor);

        Mockito.verify(sensorDao).update(sensor);
    }
}
