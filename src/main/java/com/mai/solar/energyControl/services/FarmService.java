package com.mai.solar.energyControl.services;

import com.mai.solar.energyControl.models.Farm;
import com.mai.solar.energyControl.models.SolarPanel;
import com.mai.solar.energyControl.repository.FarmRepository;
import com.mai.solar.energyControl.repository.SolarPanelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmService {

    private final FarmRepository farmRep;
    private final SolarPanelRepository panelRep;

    public FarmService(FarmRepository farmRep, SolarPanelRepository panelRep) {
        this.farmRep = farmRep;
        this.panelRep = panelRep;
    }

    public List<Farm> getAll() {
        return farmRep.findAll();
    }

    public Optional<Farm> getById(String id){
        return farmRep.findById(id);
    }

    public Farm save(Farm farm) {
        return farmRep.save(farm);
    }

    public void delete(String id) throws Exception {

        Optional<Farm> farm = farmRep.findById(id);

        if(farm.isEmpty()) {
            throw new Exception("Farm not found");
        }

        farmRep.delete(farm.get());

    }

    public void associateSolarPanel(String farmId, String panelId) throws Exception {
        Optional<SolarPanel> panelOpt = panelRep.findById(panelId);
        Optional<Farm> farmOpt = farmRep.findById(farmId);

        if (panelOpt.isEmpty() || farmOpt.isEmpty()) {
            throw new Exception("Content not found");
        }

        SolarPanel panel = panelOpt.get();
        Farm farm = farmOpt.get();

        farm.getSolarPanels().add(panel);
        panel.getFarms().add(farm);

        farmRep.save(farm);
        panelRep.save(panel);

    }

}
