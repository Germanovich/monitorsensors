package com.germanovich.monitorsensors.controller.type;

import com.germanovich.monitorsensors.controller.util.TypeConverter;
import com.germanovich.monitorsensors.dto.type.TypeDto;
import com.germanovich.monitorsensors.service.type.TypeService;
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
@RequestMapping("/types")
public class TypeController {

    private final TypeService typeService;

    @GetMapping
    @PreAuthorize("hasRole('Administrator')")
    public List<TypeDto> getTypes() {
        return TypeConverter.convertTypeListToTypeDtoList(typeService.getTypes());
    }
}
