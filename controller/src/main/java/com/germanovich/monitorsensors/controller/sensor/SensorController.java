package com.germanovich.monitorsensors.controller.sensor;

import com.germanovich.monitorsensors.controller.util.SensorConverter;
import com.germanovich.monitorsensors.dto.sensor.SensorDetailedDto;
import com.germanovich.monitorsensors.dto.sensor.SensorDto;
import com.germanovich.monitorsensors.dto.sensor.SensorShortDto;
import com.germanovich.monitorsensors.service.sensor.SensorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    @PostMapping
    @PreAuthorize("hasRole('Administrator')")
    public ResponseEntity<Void> addSensor(@RequestBody @Validated SensorDto sensorDto) {
        sensorService.addSensor(SensorConverter.convertSensorDtoToSensor(sensorDto));
        log.info("Sensor added");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('Administrator')")
    public ResponseEntity<Void> removeSensor(@PathVariable long id) {
        sensorService.removeSensor(id);
        log.info("Sensor deleted");
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    @PreAuthorize("hasRole('Administrator')")
    public ResponseEntity<Void> updateSensor(@RequestBody @Validated SensorDto sensorDto) {
        sensorService.updateSensor(SensorConverter.convertSensorDtoToSensor(sensorDto));
        log.info("Sensor updated");
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<SensorShortDto> getSensors(@RequestParam(name = "word", required = false) String word) {
        if (word == null) {
            return SensorConverter.convertSensorListToSensorShortDtoList(sensorService.getSensors());
        }
        return SensorConverter.convertSensorListToSensorShortDtoList(sensorService.getSensorsByWord(word));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Administrator')")
    public SensorDetailedDto getSensor(@PathVariable long id) {
        return SensorConverter.convertSensorToSensorDetailedDto(sensorService.getSensor(id));
    }
}