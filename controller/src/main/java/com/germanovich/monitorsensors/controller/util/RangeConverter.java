package com.germanovich.monitorsensors.controller.util;

import com.germanovich.monitorsensors.dto.range.RangeDto;
import com.germanovich.monitorsensors.model.range.Range;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RangeConverter {

    public Range convertRangeDtoToRange(final RangeDto rangeDto) {
        return rangeDto == null ? null : Range.builder()
                .to(rangeDto.getTo())
                .from(rangeDto.getFrom())
                .build();
    }

    public RangeDto convertRangeToRangeDto(final Range range) {
        return range == null ? null : RangeDto.builder()
                .to(range.getTo())
                .from(range.getFrom())
                .build();
    }
}
