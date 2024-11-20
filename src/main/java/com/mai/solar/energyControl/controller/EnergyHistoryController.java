package com.mai.solar.energyControl.controller;

import com.mai.solar.energyControl.models.EnergyHistory;
import com.mai.solar.energyControl.services.EnergyHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<EnergyHistory>> getAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {
        Page<EnergyHistory> energyHistoriesPage = energyHistoryService.getAll(page);
        List<EnergyHistory> energyHistories = energyHistoriesPage.getContent();

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
