package org.example.warehouse.controller;

import org.example.warehouse.dto.CurrencyDto;
import org.example.warehouse.model.Currency;
import org.example.warehouse.model.Result;
import org.example.warehouse.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Currency")

public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public List<Currency> getCurrencies() {
        return currencyService.getCurrencies();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public Optional<Currency> CurrencyGetById(@PathVariable Integer id) {
        return currencyService.getCurrencyById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result CurrencyPost(@RequestBody CurrencyDto currencyDto) {
        return currencyService.createCurrency(currencyDto);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result CurrencyPut(@PathVariable Integer id, @RequestBody CurrencyDto currencyDto) {
        return currencyService.updateCurrency(currencyDto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    public Result CurrencyDeleteById(@PathVariable Integer id) {
        return currencyService.deleteCurrencyById(id);
    }


}
