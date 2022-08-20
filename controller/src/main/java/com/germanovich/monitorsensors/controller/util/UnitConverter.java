package com.germanovich.monitorsensors.controller.util;

import com.germanovich.monitorsensors.dto.unit.UnitDto;
import com.germanovich.monitorsensors.model.unit.Unit;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UnitConverter {

    public UnitDto convertUnitToUnitDto(final Unit unit) {
        return UnitDto.builder()
                .name(unit.getName())
                .build();
    }

    public List<UnitDto> convertUnitListToUnitDtoList(final List<Unit> unitList) {
        List<UnitDto> unitDtoList = new ArrayList<>();
        unitList.forEach(unit -> {
            var unitDto = convertUnitToUnitDto(unit);
            if (unitDto != null) {
                unitDtoList.add(unitDto);
            }
        });
        return unitDtoList;
    }
}
