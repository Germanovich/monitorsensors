package com.germanovich.monitorsensors.dto.sensor;

import com.germanovich.monitorsensors.dto.range.RangeDto;
import com.germanovich.monitorsensors.dto.sensor.constraint.RangeConstraint;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@Validated
public class SensorDto {

    private long id;
    @NotBlank(message = "Name is mandatory")
    @Size(max = 30, message = "Size should be less then equal to 30")
    private String name;
    @NotBlank(message = "Model is mandatory")
    @Size(max = 15, message = "Size should be less then equal to 15")
    private String model;
    @NotNull(message = "Range is mandatory")
    @RangeConstraint(message = "From must be less than To")
    private RangeDto range;
    @NotBlank(message = "Type is mandatory")
    private String type;
    private String unit;
    @Size(max = 40, message = "Size should be less then equal to 40")
    private String location;
    @Size(max = 200, message = "Size should be less then equal to 200")
    private String description;
}
