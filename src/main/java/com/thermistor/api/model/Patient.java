package com.thermistor.api.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    public int room;

    @Column(nullable = false)
    public int floor;

    @Column(nullable = false)
    public String firstname;

    @Column(nullable = false)
    public String lastname;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient", orphanRemoval = true)
    private List<Measure> measures = new ArrayList<>();

    public Patient() {
    }

    public Patient(int id, int room, int floor) {
        this.id = id;
        this.room = room;
        this.floor = floor;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }


}
