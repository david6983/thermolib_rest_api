package com.thermistor.api.repository;

import com.thermistor.api.model.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, Integer> {
    @Query("select m from Measure m where m.patient.id = :patient")
    public Set<Measure> findAllByPatientId(@Param("patient") int patientId);
}
