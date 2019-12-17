package com.thermistor.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "measure")
public class Measure {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private float temperature; // celsius degree

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JsonIgnore
    private Patient patient;

    public Measure() {
    }

    public Measure(float temperature, Date date, Patient patient) {
        this.temperature = temperature;
        this.date = date;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
