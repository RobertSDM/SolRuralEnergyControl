package com.mai.solar.energyControl.repository;

import com.mai.solar.energyControl.models.EnergyLevelType;
import com.mai.solar.energyControl.models.enums.EnergyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyLevelTypeRepository extends JpaRepository<EnergyLevelType, EnergyType> {

}
