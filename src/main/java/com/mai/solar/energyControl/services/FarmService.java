package com.mai.solar.energyControl.services;

import com.mai.solar.energyControl.models.Farm;
import com.mai.solar.energyControl.repository.FarmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmService {

    private final FarmRepository farmRep;

    public FarmService(FarmRepository farmRep) {
        this.farmRep = farmRep;
    }

    public List<Farm> getAll() {
        return farmRep.findAll();
    }

    public Optional<Farm> getById(String id){
        return farmRep.findById(id);
    }

    public Farm save(Farm farm) {
        return farmRep.save(farm);
    }

    public void delete(String id) throws Exception {

        Optional<Farm> farm = farmRep.findById(id);

        if(farm.isPresent()) {
            farmRep.delete(farm.get());
        }

        throw new Exception("Farm not found");
    }

}
