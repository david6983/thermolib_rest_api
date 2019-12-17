package com.thermistor.api.web.controller;

import com.thermistor.api.model.Measure;
import com.thermistor.api.repository.MeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/measures")
public class MeasureController {
    @Autowired
    private MeasureRepository measures;

    @GetMapping({"", "/"})
    public List<Measure> listMeasures() {
        return measures.findAll();
    }

}

