package com.mai.solar.energyControl.services;

import com.mai.solar.energyControl.models.EnergyHistory;
import com.mai.solar.energyControl.repository.EnergyHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnergyHistoryService {

    private final EnergyHistoryRepository energyHistoryRep;

    public EnergyHistoryService(EnergyHistoryRepository energyHistoryRep) {
        this.energyHistoryRep = energyHistoryRep;
    }

    public List<EnergyHistory> getAll(){
        return this.energyHistoryRep.findAll();
    }

    public Optional<EnergyHistory> getById(Long id){
        return this.energyHistoryRep.findById(id);
    }

}
