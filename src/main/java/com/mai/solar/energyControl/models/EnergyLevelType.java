package com.mai.solar.energyControl.models;

import com.mai.solar.energyControl.models.enums.EnergyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "energy_level_type")
@Getter
@Setter
@Data
public class EnergyLevelType {

        @Id
        @Enumerated(EnumType.STRING)
        @NotEmpty(message = "The \"type\" cannot be null or blank")
        @Column(unique = true)
        private EnergyType type;

}
