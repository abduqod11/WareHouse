package org.example.warehouse.controller;

import org.example.warehouse.dto.MeasurementDto;
import org.example.warehouse.model.Measurement;
import org.example.warehouse.model.Result;
import org.example.warehouse.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Measurement")

public class MeasurementController {
    @Autowired
    MeasurementService measurementService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public List<Measurement> getMeasurements() {
        return measurementService.getMeasurements();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public Optional<Measurement> MeasurementGetById(@PathVariable Integer id) {
        return measurementService.getMeasurementById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result addMeasurement(@RequestBody MeasurementDto measurementDto) {
        return measurementService.addMeasurement(measurementDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result updateMeasurement(@RequestBody MeasurementDto measurementDto, @PathVariable Integer id) {
        return measurementService.updateMeasurement(measurementDto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    public Result deleteMeasurement(@PathVariable Integer id) {
        return measurementService.deleteMeasurementBId(id);
    }
}
