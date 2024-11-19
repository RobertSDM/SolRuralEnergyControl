package com.mai.solar.energyControl.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


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
    @JsonIgnoreProperties("farms")
    @JoinColumn(name = "solarPanelId")
    @ManyToOne(cascade = CascadeType.MERGE)
    private SolarPanel panelId;
    @JsonIgnoreProperties("solarPanels")
    @JoinColumn(name = "farmId")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Farm farmId;
}
