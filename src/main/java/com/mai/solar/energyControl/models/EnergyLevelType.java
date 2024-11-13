package com.mai.solar.energyControl.models;

import com.mai.solar.energyControl.models.enums.EnergyType;
import jakarta.persistence.*;
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
        private EnergyType type;

}
