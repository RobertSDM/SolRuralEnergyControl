package com.mai.solar.energyControl.controller;

import com.mai.solar.energyControl.models.Farm;
import com.mai.solar.energyControl.services.FarmService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/farm")
public class FarmController {

    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farm> getById(@PathVariable String id) {

        Optional<Farm> farm = farmService.getById(id);

        if (farm.isPresent()) {
            return ResponseEntity.ok(farm.get());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Farm>> getAll() {

        List<Farm> farms = farmService.getAll();

        if (!farms.isEmpty()) {
            return ResponseEntity.ok(
                    farms
            );
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Farm> create(@RequestBody Farm farm) {

        return ResponseEntity.ok(farmService.save(farm));
    }

    @PutMapping
    public ResponseEntity<Farm> update(@RequestBody @Valid Farm farm) {
        return ResponseEntity.ok(farmService.save(farm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Farm> delete(@PathVariable String id) {

        try {
            farmService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

}