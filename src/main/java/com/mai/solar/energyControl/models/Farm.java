package com.mai.solar.energyControl.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "farm")
@Getter
@Setter
@Data
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private LocalDate subscriptionDate = LocalDate.now();
    private Double hectareSize;
    private Integer panelCount;

    @JsonIgnoreProperties("farms")
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "farm_solarPanel",
            joinColumns = @JoinColumn(name = "farmId"),
            inverseJoinColumns = @JoinColumn(name = "solarPanelId")
    )
    private List<SolarPanel> solarPanels;

}
