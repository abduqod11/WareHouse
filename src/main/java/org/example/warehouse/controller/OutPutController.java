package org.example.warehouse.controller;

import org.example.warehouse.dto.OutPutDto;
import org.example.warehouse.model.OutPut;
import org.example.warehouse.model.Result;
import org.example.warehouse.service.OutPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/OutPut")

public class OutPutController {

    @Autowired
    OutPutService outPutService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public List<OutPut> getOutPuts() {
        return outPutService.getAllOutPut();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public Optional<OutPut> getOutPutById(@PathVariable Integer id) {
        return outPutService.getAllOutPutById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result addOutPut(@RequestBody OutPutDto outPutDto) {
        return outPutService.createOutPut(outPutDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result updateOutPut(@RequestBody OutPutDto outPutDto,@PathVariable Integer id) {
        return outPutService.updateOutPut(outPutDto,id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    public Result deleteOutPutById(@PathVariable Integer id) {
        return outPutService.deleteOutPutById(id);
    }
}
