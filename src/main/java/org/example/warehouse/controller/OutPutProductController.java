package org.example.warehouse.controller;

import org.example.warehouse.dto.OutPutProductDto;
import org.example.warehouse.model.OutPutProduct;
import org.example.warehouse.model.Result;
import org.example.warehouse.service.OutPutProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/OutPutProduct")

public class OutPutProductController {

    @Autowired
    OutPutProductService outPutProductService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public List<OutPutProduct> getAllPro(){
        return outPutProductService.getOutPutProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public OutPutProduct getProAllById(@PathVariable Integer id){
        return outPutProductService.getOutPutProductById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result createOutPutPro(@RequestBody OutPutProductDto outPutProductDto){
        return outPutProductService.createOutPutProduct(outPutProductDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result updateOutPutPro(@RequestBody OutPutProductDto outPutProductDto,@PathVariable Integer id){
        return outPutProductService.updateOutPutProduct(outPutProductDto,id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    public Result deleteOutPutPro(@PathVariable Integer id){
        return outPutProductService.deleteOutPutProduct(id);
    }
}
