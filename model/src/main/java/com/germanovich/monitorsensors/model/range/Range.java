package com.germanovich.monitorsensors.model.range;

import com.germanovich.monitorsensors.model.AEntity;
import com.germanovich.monitorsensors.model.sensor.Sensor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ranges")
public class Range extends AEntity {

    @Column(name = "range_from")
    private int from;
    @Column(name = "range_to")
    private int to;
}
