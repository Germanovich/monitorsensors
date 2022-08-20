package com.germanovich.monitorsensors.dto.sensor.constraint;

import com.germanovich.monitorsensors.dto.range.RangeDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RangeConstraintValidator implements ConstraintValidator<RangeConstraint, RangeDto> {

    @Override
    public boolean isValid(RangeDto rangeDto, ConstraintValidatorContext constraintValidatorContext) {
        return rangeDto.getFrom() < rangeDto.getTo();
    }
}