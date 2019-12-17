package com.thermistor.api.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "measure")
public class Measure {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    public float temperature; // celsius degree

    @Column(nullable = false)
    public Date date;

    @ManyToOne
    public Patient patient;

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
