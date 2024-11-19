package com.mai.solar.energyControl.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate registryDate = LocalDate.now();
    @Min(value = 0, message = "The \"energy value\" cannot be null or blank")
    private Double energyValue;

    @JoinColumn(name = "energyLevelTypeId")
    @ManyToOne(cascade = CascadeType.MERGE)
    private EnergyLevelType levelTypeId;
    @JoinColumn(name = "solarPanelId")
    @ManyToOne(cascade = CascadeType.MERGE)
    private SolarPanel panelId;
    @JoinColumn(name = "farmId")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Farm farmId;

}
