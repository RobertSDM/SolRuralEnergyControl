package com.mai.solar.energyControl.models;

import jakarta.persistence.*;
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
    private Integer power;
    private Integer voltage;
    private Double price;
    private Integer cellQuantity;
    @JoinColumn(name = "panelId", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.DETACH)
    private Farm farm;

}
