package com.mai.solar.energyControl.controller;

import com.mai.solar.energyControl.models.Farm;
import com.mai.solar.energyControl.models.dto.FarmDTO;
import com.mai.solar.energyControl.services.FarmService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @Value("${pagination.default.size}")
    private Integer defaultSize;

    @PatchMapping("/{farmId}/panel/{panelId}")
    private ResponseEntity<Farm> associateFarm(@PathVariable String panelId, @PathVariable String farmId) throws Exception {
        farmService.associateSolarPanel(farmId, panelId);
        return ResponseEntity.ok().build();

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
    public ResponseEntity<List<Farm>> getAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {

        Pageable pageable = PageRequest.of(page, defaultSize);

        Page<Farm> farmsPage = farmService.getAll(pageable);

        List<Farm> farms = farmsPage.getContent();

        if (!farms.isEmpty()) {
            return ResponseEntity.ok(
                    farms
            );
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Farm> create(@RequestBody FarmDTO farmDTO) {

        Farm farm = new Farm();

        BeanUtils.copyProperties(farmDTO, farm);

        return ResponseEntity.ok(farmService.save(farm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farm> update(@RequestBody @Valid Farm farm, @PathVariable String id) {
        farmService.update(farm, id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Farm> delete(@PathVariable String id) {

        try {
            farmService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

}
