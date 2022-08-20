package com.germanovich.monitorsensors.model.sensor;

import com.germanovich.monitorsensors.model.AEntity;
import com.germanovich.monitorsensors.model.range.Range;
import com.germanovich.monitorsensors.model.type.Type;
import com.germanovich.monitorsensors.model.unit.Unit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sensors")
public class Sensor extends AEntity {

    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @Column(name = "model", nullable = false, length = 40)
    private String model;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "range_id", referencedColumnName = "id")
    private Range range;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "type_id", insertable = false, updatable = false, nullable = false)
    private Type type;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "unit_id", insertable = false, updatable = false)
    private Unit unit;
    @Column(name = "location", length = 40)
    private String location;
    @Column(name = "description", length = 200)
    private String description;
}
