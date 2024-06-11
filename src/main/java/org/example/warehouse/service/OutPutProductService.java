package org.example.warehouse.service;

import org.example.warehouse.dto.OutPutProductDto;
import org.example.warehouse.model.OutPut;
import org.example.warehouse.model.OutPutProduct;
import org.example.warehouse.model.Result;
import org.example.warehouse.repository.OutPutProductRepository;
import org.example.warehouse.repository.OutPutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class OutPutProductService {

    @Autowired
    OutPutProductRepository outPutProductRepository;
    @Autowired
    OutPutRepository outPutRepository;

    public List<OutPutProduct> getOutPutProducts() {
        return outPutProductRepository.findAll();
    }

    public OutPutProduct getOutPutProductById(Integer id) {
        Optional<OutPutProduct> outPutProduct = outPutProductRepository.findById(id);
        return outPutProduct.get();
    }

    public Result createOutPutProduct(OutPutProductDto outPutProductDto) {
        OutPutProduct outPutProduct = new OutPutProduct();
        outPutProduct.setAmount(outPutProductDto.getAmount());
        outPutProduct.setPrice(outPutProductDto.getPrice());

        Optional<OutPut> outPutOptional = outPutRepository.findById(outPutProductDto.getOutput());
        OutPut outPut = outPutOptional.get();
        outPutProduct.setOutPut(outPut);
        outPutProductRepository.save(outPutProduct);
        return new Result(true,"OutPutProduct Added successfully");
    }

    public Result updateOutPutProduct(OutPutProductDto outPutProductDto,Integer id) {
        Optional<OutPutProduct> outPutProductOptional = outPutProductRepository.findById(id);
        if(outPutProductOptional.isPresent()) {
            OutPutProduct outPutProduct = outPutProductOptional.get();
            outPutProduct.setAmount(outPutProductDto.getAmount());
            outPutProduct.setPrice(outPutProductDto.getPrice());
            Optional<OutPut> outPutOptional = outPutRepository.findById(outPutProductDto.getOutput());
            OutPut outPut = outPutOptional.get();
            outPutProduct.setOutPut(outPut);
            outPutProductRepository.save(outPutProduct);
            return new Result(true,"OutPutProduct Updated successfully");
        }
        return new Result(false,"OutPutProduct Not Found");
    }

    public Result deleteOutPutProduct(Integer id) {
        outPutProductRepository.deleteById(id);
        return new Result(true,"OutPutProduct Deleted successfully");
    }
}