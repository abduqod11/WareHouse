package org.example.warehouse.service;

import org.example.warehouse.dto.OutPutDto;
import org.example.warehouse.model.Client;
import org.example.warehouse.model.OutPut;
import org.example.warehouse.model.Result;
import org.example.warehouse.model.WareHouse;
import org.example.warehouse.repository.ClientRepository;
import org.example.warehouse.repository.OutPutRepository;
import org.example.warehouse.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class OutPutService {

    @Autowired
    OutPutRepository outPutRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    WareHouseRepository wareHouseRepository;

    public List<OutPut> getAllOutPut() {
        return outPutRepository.findAll();
    }

    public Optional<OutPut> getAllOutPutById(Integer id) {
        return outPutRepository.findById(id);
    }

    public Result createOutPut(OutPutDto outPutDto){
        OutPut outPut = new OutPut();
        outPut.setFactureNumber(outPutDto.getFactureNumber());
        outPut.setCode(outPutDto.getCode());
        Optional<Client> clientOptional = clientRepository.findById(outPutDto.getClientId());
        Client client = clientOptional.get();
        Optional<WareHouse> wareHouse = wareHouseRepository.findById(outPutDto.getWarehouseId());
        WareHouse wareHouse1 = wareHouse.get();
        outPut.setClient(client);
        outPut.setWareHouse(wareHouse1);
        outPut.setFactureNumber(outPutDto.getFactureNumber());
        outPutRepository.save(outPut);
        return new Result(true,"OutPut created successfully");
    }

    public Result updateOutPut( OutPutDto outPutDto, Integer id) {
        Optional<OutPut> outPutOptional = outPutRepository.findById(id);
        if (outPutOptional.isPresent()) {
            OutPut outPut = outPutOptional.get();
            outPut.setCode(outPutDto.getCode());
            outPut.setFactureNumber(outPutDto.getFactureNumber());

            Optional<Client> optionalClient = clientRepository.findById(outPutDto.getClientId());
            Client client = optionalClient.get();

            Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(outPutDto.getWarehouseId());
            WareHouse wareHouse1 = optionalWareHouse.get();

            outPut.setClient(client);
            outPut.setWareHouse(wareHouse1);
            outPutRepository.save(outPut);

            return new Result(true,"OutPut Updated successfully");
        }
        return new Result(false,"OutPut Not Found");
    }

    public Result deleteOutPutById(Integer id) {
        outPutRepository.deleteById(id);
        return new Result(true,"OutPut deleted successfully");
    }
}
