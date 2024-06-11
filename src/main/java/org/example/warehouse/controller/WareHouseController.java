package org.example.warehouse.controller;

import org.example.warehouse.dto.WareHouseDto;
import org.example.warehouse.model.Result;
import org.example.warehouse.model.WareHouse;
import org.example.warehouse.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/WareHouse")

public class WareHouseController {

    @Autowired
    WareHouseService wareHouseService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public List<WareHouse> getAllWareHouses() {
        return wareHouseService.getAllWareHouses();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public WareHouse getWareHouseById(@PathVariable Integer id) {
        return wareHouseService.getWareHouseById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result createWareHouse(@RequestBody WareHouseDto wareHouseDto) {
        return wareHouseService.addWareHouse(wareHouseDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result updateWareHouse(@PathVariable Integer id, WareHouseDto wareHouseDto) {
        return wareHouseService.updateWareHouse(wareHouseDto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    public Result deleteWareHouseById(@PathVariable Integer id) {
        wareHouseService.deleteWareHouseById(id);
        return new Result(true,"WareHouse deleted successfully");
    }
}
