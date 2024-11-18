package com.mai.solar.energyControl.services;

import com.mai.solar.energyControl.models.SolarPanel;
import com.mai.solar.energyControl.repository.SolarPanelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolarPanelService {

    private final SolarPanelRepository panelRep;

    public SolarPanelService(SolarPanelRepository panelRep) {
        this.panelRep = panelRep;
    }

    public List<SolarPanel> getAll() {
        return panelRep.findAll();
    }

    public Optional<SolarPanel> getById(String id){
        return panelRep.findById(id);
    }

    public SolarPanel save(SolarPanel farm) {
        return panelRep.save(farm);
    }

    public void delete(String id) {

        Optional<SolarPanel> panel = panelRep.findById(id);

            panelRep.delete(panel.orElse(new SolarPanel()));

    }
    
}
