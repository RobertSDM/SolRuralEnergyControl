package com.mai.solar.energyControl.controller;

import com.mai.solar.energyControl.models.EnergyHistory;
import com.mai.solar.energyControl.services.EnergyHistoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/energy-history")
public class EnergyHistoryController {

    private final EnergyHistoryService energyHistoryService;

    @Value("${pagination.default.size}")
    private Integer defaultSize;

    public EnergyHistoryController(EnergyHistoryService energyHistoryService) {
        this.energyHistoryService = energyHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<EnergyHistory>> getAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {

        Pageable pageable = PageRequest.of(page, defaultSize);

        Page<EnergyHistory> energyHistoriesPage = energyHistoryService.getAll(pageable);

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
