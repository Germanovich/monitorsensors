package com.germanovich.monitorsensors.controller.unit;

import com.germanovich.monitorsensors.controller.util.TypeConverter;
import com.germanovich.monitorsensors.controller.util.UnitConverter;
import com.germanovich.monitorsensors.dto.type.TypeDto;
import com.germanovich.monitorsensors.dto.unit.UnitDto;
import com.germanovich.monitorsensors.service.unit.UnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/units")
public class UnitController {

    private final UnitService unitService;

    @GetMapping
    @PreAuthorize("hasRole('Administrator')")
    public List<UnitDto> getSensors() {
        return UnitConverter.convertUnitListToUnitDtoList(unitService.getUnits());
    }
}
