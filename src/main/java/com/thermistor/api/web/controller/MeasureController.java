package com.thermistor.api.web.controller;

import com.thermistor.api.model.Measure;
import com.thermistor.api.model.Patient;
import com.thermistor.api.repository.MeasureRepository;
import com.thermistor.api.repository.PatientRepository;
import com.thermistor.api.web.exceptions.PatientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/measures")
public class MeasureController {
    @Autowired
    private PatientRepository patients;

    @Autowired
    private MeasureRepository measures;

    @GetMapping({"", "/"})
    public List<Measure> listMeasures() {
        return measures.findAll();
    }

    @GetMapping("/{patientId}")
    public Set<Measure> listMeasuresFromPatient(@PathVariable int patientId) {
        return measures.findAllByPatientId(patientId);
    }

    @PostMapping("/{patientId}")
    public ResponseEntity<Measure> addMesure(
            @RequestBody Measure measure,
            @PathVariable int patientId
    ) {
        Patient foundPatient = patients
                .findById(patientId)
                .orElseThrow(
                        () -> new PatientNotFoundException(
                                "Can't find patient of id " + patientId
                        )
                );
        if (foundPatient == null) {
            return ResponseEntity.noContent().build();
        }

        measure.setPatient(foundPatient);
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

    @DeleteMapping("/{patientId}")
    public void deleteMeasureForUser(@PathVariable int patientId) {
        measures.deleteAllByPatientId(patientId);
    }
}

