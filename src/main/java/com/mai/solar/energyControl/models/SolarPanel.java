package com.mai.solar.energyControl.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
    @Min(0)
    private Integer power;
    @Min(0)
    private Integer voltage;
    @Min(0)
    private Double price;
    @Min(0)
    private Integer cellQuantity;

    @JsonIgnoreProperties("solarPanels")
    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "solarPanels")
    private List<Farm> farms;
}
