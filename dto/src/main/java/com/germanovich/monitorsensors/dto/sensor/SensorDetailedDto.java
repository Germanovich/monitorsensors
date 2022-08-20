package com.germanovich.monitorsensors.dto.sensor;

import com.germanovich.monitorsensors.dto.range.RangeDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorDetailedDto {

    private String name;
    private String model;
    private RangeDto range;
    private String type;
    private String unit;
    private String location;
    private String description;
}
