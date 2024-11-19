package com.mai.solar.energyControl.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "solar_panel")
@Getter
@Setter
@Data
public class SolarPanel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer power;
    private Integer voltage;
    private Double price;
    private Integer cellQuantity;

    @JsonIgnoreProperties("solarPanels")
    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "solarPanels")
    private List<Farm> farms;
}
