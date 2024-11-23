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
public class FarmService {

    private final FarmRepository farmRep;
    private final SolarPanelRepository panelRep;

    @Value("${pagination.default.size}")
    private Integer defaultSize;

    public FarmService(FarmRepository farmRep, SolarPanelRepository panelRep) {
        this.farmRep = farmRep;
        this.panelRep = panelRep;
    }

    public List<Farm> getAll() {
        return farmRep.findAll();
    }

    public Page<Farm> getAll(Pageable pageable) {


        return this.farmRep.findAll(pageable);
    }

    public void update(Farm farm, String id) {

        farm.setId(id);
        farmRep.save(farm);
    }

    public Optional<Farm> getById(String id) {
        return this.farmRep.findById(id);
    }

    public Farm save(Farm farm) {
        return this.farmRep.save(farm);
    }

    public void delete(String id) throws Exception {

        Optional<Farm> farm = this.farmRep.findById(id);

        if (farm.isEmpty()) {
            throw new Exception("Farm not found");
        }

        this.farmRep.delete(farm.get());

    }

    public void associateSolarPanel(String farmId, String panelId) throws Exception {
        Optional<SolarPanel> panelOpt = panelRep.findById(panelId);
        Optional<Farm> farmOpt = this.farmRep.findById(farmId);

        if (panelOpt.isEmpty() || farmOpt.isEmpty()) {
            throw new Exception("Content not found");
        }

        SolarPanel panel = panelOpt.get();
        Farm farm = farmOpt.get();

        farm.getSolarPanels().add(panel);
        panel.getFarms().add(farm);

        farm.setId(farmId);
        panel.setId(panelId);

        this.farmRep.save(farm);
        panelRep.save(panel);

    }

}
