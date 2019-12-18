package com.thermistor.api.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int room;

    @Column(nullable = false)
    private int floor;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "sensor", orphanRemoval = true)
    private List<Measure> measures = new ArrayList<>();

    public Sensor() {
    }

    public Sensor(int id, int room, int floor) {
        this.id = id;
        this.room = room;
        this.floor = floor;
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
