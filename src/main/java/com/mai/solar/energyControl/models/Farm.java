package com.mai.solar.energyControl.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

}
