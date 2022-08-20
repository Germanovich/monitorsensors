package com.germanovich.monitorsensors.dto.sensor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorShortDto {

    private String name;
    private String model;
}
