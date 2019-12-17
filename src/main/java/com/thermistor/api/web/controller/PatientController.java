package com.thermistor.api.web.controller;

import com.thermistor.api.model.Patient;
import com.thermistor.api.repository.PatientRepository;
import com.thermistor.api.web.exceptions.PatientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientRepository patients;

    /**
     * Get all patients list.
     *
     * @return the list of patient
     */
    @GetMapping({"", "/"})
    public List<Patient> listPatients() {
        return patients.findAll();
    }

    /**
     * Get patient by id
     *
     * @param id patient id
     * @return patients
     */
    @GetMapping(value="/{id}")
    public Patient getPatient(@PathVariable int id)
    {
        return patients.findById(id).orElseThrow(() -> new PatientNotFoundException("Can't find patient of id " + id));
    }

    @DeleteMapping(value="/{id}")
    public void deletePatient(@PathVariable int id) {
        patients.deleteById(id);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient addedPatient = patients.save(patient);
        if (addedPatient == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedPatient.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
