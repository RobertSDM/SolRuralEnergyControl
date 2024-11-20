package com.mai.solar.energyControl.services;

import com.mai.solar.energyControl.models.Farm;
import com.mai.solar.energyControl.models.SolarPanel;
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
public class SolarPanelService {

    private final SolarPanelRepository panelRep;
    private final FarmRepository farmRep;





    public SolarPanelService(SolarPanelRepository panelRep, FarmRepository farmRep) {
        this.panelRep = panelRep;
        this.farmRep = farmRep;
    }

    public List<SolarPanel> getAll() {
        return panelRep.findAll();
    }

    public Page<SolarPanel> getAll(Pageable pageable) {

        return this.panelRep.findAll(pageable);
    }

    public Optional<SolarPanel> getById(String id) {
        return panelRep.findById(id);
    }

    public SolarPanel save(SolarPanel farm) {
        return panelRep.save(farm);
    }

    public void delete(String id) {

        Optional<SolarPanel> panel = panelRep.findById(id);

        panelRep.delete(panel.orElse(new SolarPanel()));

    }

    public void associateFarm(String panelId, String farmId) throws Exception {

        Optional<Farm> farmOpt = farmRep.findById(farmId);
        Optional<SolarPanel> panelOpt = panelRep.findById(panelId);

        if (panelOpt.isEmpty() || farmOpt.isEmpty()) {
            throw new Exception("Content not found");
        }

        Farm farm = farmOpt.get();
        SolarPanel panel = panelOpt.get();

        panel.getFarms().add(farm);
        farm.getSolarPanels().add(panel);

        panelRep.save(panel);
        farmRep.save(farm);
    }

}
