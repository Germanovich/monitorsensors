package com.germanovich.monitorsensors.controller.util;

import com.germanovich.monitorsensors.dto.type.TypeDto;
import com.germanovich.monitorsensors.model.type.Type;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class TypeConverter {

    public TypeDto convertTypeToTypeDto(final Type type) {
        return TypeDto.builder()
                .name(type.getName())
                .build();
    }

    public static List<TypeDto> convertTypeListToTypeDtoList(final List<Type> typeList) {
        List<TypeDto> typeDtoList = new ArrayList<>();
        typeList.forEach(type -> {
            var typeDto = convertTypeToTypeDto(type);
            if (typeDto != null) {
                typeDtoList.add(typeDto);
            }
        });
        return typeDtoList;
    }
}
