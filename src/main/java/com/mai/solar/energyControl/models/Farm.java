package com.mai.solar.energyControl.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Size(min = 3, max = 55, message = "The \"name\" length needs to be between 3 and 55")
    @NotEmpty(message = "The \"name\" cannot be null or blank")
    @Column(unique = true)
    private String name;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate subscriptionDate = LocalDate.now();
    @Min(0)
    private Double hectareSize;
    @Min(0)
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
