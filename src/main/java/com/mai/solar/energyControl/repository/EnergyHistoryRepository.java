package com.mai.solar.energyControl.repository;

import com.mai.solar.energyControl.models.EnergyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyHistoryRepository extends JpaRepository<EnergyHistory, Long> {



}
