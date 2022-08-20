package com.germanovich.monitorsensors.model.unit;

import com.germanovich.monitorsensors.model.AEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "units")
public class Unit extends AEntity {

    @Column(name = "name")
    private String name;
}
