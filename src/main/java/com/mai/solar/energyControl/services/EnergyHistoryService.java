package com.mai.solar.energyControl.services;

import com.mai.solar.energyControl.models.EnergyHistory;
import com.mai.solar.energyControl.models.EnergyLevelType;
import com.mai.solar.energyControl.models.Farm;
import com.mai.solar.energyControl.models.SolarPanel;
import com.mai.solar.energyControl.models.dto.EnergyHistoryHiveMQDTO;
import com.mai.solar.energyControl.models.enums.EnergyType;
import com.mai.solar.energyControl.repository.EnergyHistoryRepository;
import com.mai.solar.energyControl.repository.EnergyLevelTypeRepository;
import com.mai.solar.energyControl.repository.FarmRepository;
import com.mai.solar.energyControl.repository.SolarPanelRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnergyHistoryService {

    private final EnergyHistoryRepository energyHistoryRep;
    private final FarmRepository farmRep;
    private final SolarPanelRepository panelRep;
    private final EnergyLevelTypeRepository energyLevelTypeRep;



    public EnergyHistoryService(EnergyHistoryRepository energyHistoryRep, FarmRepository farmRep, SolarPanelRepository panelRep, EnergyLevelTypeRepository energyLevelTypeRep) {
        this.energyHistoryRep = energyHistoryRep;
        this.farmRep = farmRep;
        this.panelRep = panelRep;
        this.energyLevelTypeRep = energyLevelTypeRep;
    }

    public List<EnergyHistory> getAll(){
        return this.energyHistoryRep.findAll();
    }
  
    public Page<EnergyHistory> getAll(Pageable pageable){

        return this.energyHistoryRep.findAll(pageable);
    }

    public Optional<EnergyHistory> getById(Long id){
        return this.energyHistoryRep.findById(id);
    }

    public EnergyHistory save(EnergyHistory energyHistory){
        return this.energyHistoryRep.save(energyHistory);
    }

    public EnergyHistory create(String panelId, String farmId, Double energyValue) throws Exception {
        EnergyHistory energyHistory = new EnergyHistory();
        EnergyLevelType energyLevelType;

        Optional<Farm> farmOpt = farmRep.findById(farmId);
        Optional<SolarPanel> panelOpt = panelRep.findById(panelId);

        if (farmOpt.isEmpty() || panelOpt.isEmpty()) {
            throw new Exception("Farm not found");
        }

        Farm farm = farmOpt.get();
        SolarPanel panel = panelOpt.get();

        energyHistory.setFarmId(farm);
        energyHistory.setPanelId(panel);
        energyHistory.setEnergyValue(energyValue);

        if(energyValue < 10){
            energyLevelType = energyLevelTypeRep.findById(EnergyType.LOW).orElse(null);
        }else if(energyValue < 50){
            energyLevelType = energyLevelTypeRep.findById(EnergyType.MEDIUM).orElse(null);
        }else {
            energyLevelType = energyLevelTypeRep.findById(EnergyType.HIGH).orElse(null);
        }

        energyHistory.setLevelTypeId(energyLevelType);

        return energyHistory;
    }

}
