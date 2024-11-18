package com.mai.solar.energyControl.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "energy_history")
@Getter
@Setter
@Data
public class EnergyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private LocalDate registryDate = LocalDate.now();
    private Double energyValue;

    @JoinColumn(name = "energyLevelTypeId")
    @ManyToOne(cascade = CascadeType.DETACH)
    private EnergyLevelType levelTypeId;
    @JoinColumn(name = "solarPanelId")
    @ManyToOne(cascade = CascadeType.DETACH)
    private SolarPanel panelId;
    @JoinColumn(name = "farmId")
    @ManyToOne(cascade = CascadeType.DETACH)
    private Farm farmId;

}
