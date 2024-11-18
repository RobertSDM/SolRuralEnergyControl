package com.mai.solar.energyControl.controller;

import com.mai.solar.energyControl.models.EnergyHistory;
import com.mai.solar.energyControl.services.EnergyHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/energy-history")
public class EnergyHistoryController {

    private final EnergyHistoryService energyHistoryService;

    public EnergyHistoryController(EnergyHistoryService energyHistoryService) {
        this.energyHistoryService = energyHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<EnergyHistory>> getAll() {
        List<EnergyHistory> energyHistories = energyHistoryService.getAll();

        if (!energyHistories.isEmpty()) {
            return ResponseEntity.ok(
                    energyHistories
            );
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnergyHistory> getById(@PathVariable Long id) {

        Optional<EnergyHistory> energyHistory = energyHistoryService.getById(id);

        if (energyHistory.isPresent()) {
            return ResponseEntity.ok(energyHistory.get());
        }

        return ResponseEntity.notFound().build();
    }
}
