package com.thermistor.api.repository;

import com.thermistor.api.model.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, Integer> {
    @Query("select m from Measure m where m.sensor.id = :sensor")
    Set<Measure> findAllBySensorId(@Param("sensor") int sensorId);

    @Query("delete from Measure m where m.sensor.id = :sensor")
    @Transactional
    @Modifying
    void deleteAllBySensorId(@Param("sensor") int sensorId);
}
