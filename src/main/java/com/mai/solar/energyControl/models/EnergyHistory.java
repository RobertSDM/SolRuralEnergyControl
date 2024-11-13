package com.mai.solar.energyControl.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
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

    @JoinColumn(name = "energyHistoryId", nullable = false)
    @OneToMany(cascade = CascadeType.DETACH)
    private List<EnergyLevelType> levelTypeId;
    @JoinColumn(name = "energyHistoryId", nullable = false)
    @OneToMany(cascade = CascadeType.DETACH)
    private List<SolarPanel> panelId;
    @JoinColumn(name = "energyHistoryId", nullable = false)
    @OneToMany(cascade = CascadeType.DETACH)
    private List<Farm> farmId;

}
