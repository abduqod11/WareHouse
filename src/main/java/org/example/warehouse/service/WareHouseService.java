package org.example.warehouse.service;

import org.example.warehouse.dto.WareHouseDto;
import org.example.warehouse.model.Result;
import org.example.warehouse.model.WareHouse;
import org.example.warehouse.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class WareHouseService {

    @Autowired
    WareHouseRepository wareHouseRepository;

    public List<WareHouse> getAllWareHouses() {
        return wareHouseRepository.findAll();
    }

    public WareHouse getWareHouseById(Integer id) {
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(id);
        return optionalWareHouse.get();
    }

    public Result addWareHouse(WareHouseDto wareHousedto) {
        WareHouse wareHouse = new WareHouse();
        wareHouse.setName(wareHousedto.getName());
        wareHouse.setActive(wareHousedto.isActive());
        return new Result(true,"WareHouse added successfully");
    }

    public Result updateWareHouse(WareHouseDto wareHousedto,Integer id) {
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(id);
        if(optionalWareHouse.isPresent()){
            WareHouse wareHouse = optionalWareHouse.get();
            wareHouse.setName(wareHousedto.getName());
            wareHouse.setActive(wareHousedto.isActive());
            return new Result(true,"WareHouse updated successfully");
        }
        return new Result(false,"WareHouse not found");
    }

    public Result deleteWareHouseById(Integer id) {
        wareHouseRepository.deleteById(id);
        return new Result(true,"WareHouse deleted successfully");
    }
}
