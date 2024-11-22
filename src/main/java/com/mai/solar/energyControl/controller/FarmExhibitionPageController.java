package com.mai.solar.energyControl.controller;

import com.mai.solar.energyControl.models.Farm;
import com.mai.solar.energyControl.services.FarmService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class FarmExhibitionPageController {

    private final FarmService farmService;

    @Value("${pagination.default.size}")
    private Integer defaultSize;

    public FarmExhibitionPageController(FarmService farmService) {
        this.farmService = farmService;
    }

    @GetMapping("/farm-exhibition")
    public ModelAndView dashboard(
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {
        ModelAndView mv = new ModelAndView("farm_exhibition");
        Pageable pageable = PageRequest.of(page, defaultSize);

        Page<Farm> farmsPage = farmService.getAll(pageable);
        List<Farm> farms = farmsPage.getContent();

        mv.addObject("farms", farms);
        mv.addObject("page", page);

        return mv;
    }

}
