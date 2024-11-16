package com.mai.solar.energyControl.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


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
    @JoinColumn(name = "panelId", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.DETACH)
    private Farm farm;

}
