package org.example.warehouse.service;

import org.example.warehouse.dto.MeasurementDto;
import org.example.warehouse.model.Measurement;
import org.example.warehouse.model.Result;
import org.example.warehouse.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service

public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public List<Measurement> getMeasurements() {
        return measurementRepository.findAll();
    }

    public Optional<Measurement> getMeasurementById(Integer id) {
        return measurementRepository.findById(id);
    }

    public Result addMeasurement(MeasurementDto measurementDto) {
        Measurement measurement = new Measurement();
        measurement.setName(measurementDto.getName());
        measurement.setActive(measurementDto.isActive());
        measurementRepository.save(measurement);
        return new Result(true,"Measurement added successfully");
    }

    public Result updateMeasurement(MeasurementDto measurementDto, Integer id) {
        Optional<Measurement> measurementOptional = measurementRepository.findById(id);
        if (measurementOptional.isPresent()) {
            Measurement measurement = measurementOptional.get();
            measurement.setName(measurementDto.getName());
            measurement.setActive(measurementDto.isActive());
            measurementRepository.save(measurement);
            return new Result(true,"Measurement updated successfully");
        }
        return new Result(false,"Measurement not found");
    }

    public Result deleteMeasurementBId(Integer id) {
        measurementRepository.deleteById(id);
        return new Result(true,"Measurement deleted successfully");
    }

}
