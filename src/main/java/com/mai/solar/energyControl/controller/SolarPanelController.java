package com.mai.solar.energyControl.controller;

import com.mai.solar.energyControl.models.Farm;
import com.mai.solar.energyControl.models.SolarPanel;
import com.mai.solar.energyControl.services.SolarPanelService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solar-panel")
public class SolarPanelController {

    public final SolarPanelService panelService;

    @Value("${pagination.default.size}")
    private Integer defaultSize;

    public SolarPanelController(SolarPanelService panelService) {
        this.panelService = panelService;
    }

    @PatchMapping("/{panelId}/farm/{farmId}")
    private ResponseEntity<Farm> associateFarm(@PathVariable String panelId, @PathVariable String farmId) throws Exception {
        panelService.associateFarm(panelId, farmId);
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity<List<SolarPanel>> getAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {


        Pageable pageable = PageRequest.of(page, defaultSize);

        Page<SolarPanel> panelsPage = panelService.getAll(pageable);

        List<SolarPanel> panels = panelsPage.getContent();

        if (!panels.isEmpty()) {
            return ResponseEntity.ok(
                    panels
            );
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolarPanel> getById(@PathVariable String id) {
        Optional<SolarPanel> panel = panelService.getById(id);

        if (panel.isPresent()) {
            return ResponseEntity.ok(panel.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<SolarPanel> create(@RequestBody SolarPanel solarPanel) {
        return ResponseEntity.ok(panelService.save(solarPanel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolarPanel> update(@RequestBody SolarPanel solarPanel, @PathVariable String id) {
        panelService.update(solarPanel, id);

        return ResponseEntity.ok().build();
    }

}
