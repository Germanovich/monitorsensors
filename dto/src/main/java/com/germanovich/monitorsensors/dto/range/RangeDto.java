package com.germanovich.monitorsensors.dto.range;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RangeDto {

    private int from;
    private int to;
}
