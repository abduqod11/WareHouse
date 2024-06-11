package org.example.warehouse.repository;

import org.example.warehouse.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}
