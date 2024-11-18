package com.mai.solar.energyControl.controller;

import com.mai.solar.energyControl.models.SolarPanel;
import com.mai.solar.energyControl.services.SolarPanelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solar-panel")
public class SolarPanelController {

    public final SolarPanelService panelService;

    public SolarPanelController(SolarPanelService panelService) {
        this.panelService = panelService;
    }

    @GetMapping
    public ResponseEntity<List<SolarPanel>> getAll() {
        List<SolarPanel> panels = panelService.getAll();

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

    @PutMapping
    public ResponseEntity<SolarPanel> update(@RequestBody SolarPanel solarPanel) {
        return ResponseEntity.ok(panelService.save(solarPanel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SolarPanel> delete(@PathVariable String id) {

        panelService.delete(id);

        return ResponseEntity.ok().build();
    }

}
