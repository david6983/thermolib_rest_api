package com.thermistor.api.web.controller;

import com.thermistor.api.model.Measure;
import com.thermistor.api.model.Sensor;
import com.thermistor.api.repository.MeasureRepository;
import com.thermistor.api.repository.SensorRepository;
import com.thermistor.api.web.exceptions.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/measures")
public class MeasureController {
    @Autowired
    private SensorRepository sensors;

    @Autowired
    private MeasureRepository measures;

    @GetMapping({"", "/"})
    public List<Measure> listMeasures() {
        return measures.findAll();
    }

    @GetMapping("/{sensorId}")
    public Set<Measure> listMeasuresFromSensor(@PathVariable int sensorId) {
        return measures.findAllBySensorId(sensorId);
    }

    @PostMapping("/{sensorId}")
    public ResponseEntity<Measure> addMesure(
            @RequestBody Measure measure,
            @PathVariable int sensorId
    ) {
        Sensor foundSensor = sensors
                .findById(sensorId)
                .orElseThrow(
                        () -> new SensorNotFoundException(
                                "Can't find sensor of id " + sensorId
                        )
                );
        if (foundSensor == null) {
            return ResponseEntity.noContent().build();
        }

        measure.setSensor(foundSensor);
        Measure addedMeasure = measures.save(measure);
        if (addedMeasure == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedMeasure.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{sensorId}")
    public void deleteMeasureForSensor(@PathVariable int sensorId) {
        measures.deleteAllBySensorId(sensorId);
    }
}

