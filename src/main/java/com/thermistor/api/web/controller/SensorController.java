package com.thermistor.api.web.controller;

import com.thermistor.api.model.Sensor;
import com.thermistor.api.repository.SensorRepository;
import com.thermistor.api.web.exceptions.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sensors")
@CrossOrigin(origins = "*")
public class SensorController {
    @Autowired
    private SensorRepository sensors;

    /**
     * Get all sensors list.
     *
     * @return the list of sensors
     */
    @GetMapping({"", "/"})
    public List<Sensor> listSensors() {
        return sensors.findAll();
    }

    /**
     * Get sensor by id
     *
     * @param id sensor id
     * @return sensors
     */
    @GetMapping(value="/{id}")
    public Sensor getSensor(@PathVariable int id)
    {
        return sensors.findById(id).orElseThrow(() -> new SensorNotFoundException("Can't find sensor of id " + id));
    }

    @DeleteMapping(value="/{id}")
    public void deleteSensor(@PathVariable int id) {
        sensors.deleteById(id);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Sensor> addSensor(@RequestBody Sensor sensor) {
        Sensor addedSensor = sensors.save(sensor);
        if (addedSensor == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedSensor.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
