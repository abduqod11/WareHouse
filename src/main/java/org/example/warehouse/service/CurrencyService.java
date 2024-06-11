package org.example.warehouse.service;

import org.example.warehouse.dto.CurrencyDto;
import org.example.warehouse.model.Currency;
import org.example.warehouse.model.Result;
import org.example.warehouse.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service

public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }

    public Optional<Currency> getCurrencyById(Integer id) {
        return currencyRepository.findById(id);
    }
    public Result createCurrency(CurrencyDto currencyDto) {
        Currency currency = new Currency();
        currency.setName(currencyDto.getName());
        currency.setActive(currencyDto.isActive());
        currencyRepository.save(currency);
        return new Result(true,"Currency created successfully");
    }

    public Result updateCurrency(CurrencyDto currencyDto,Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if(optionalCurrency.isPresent()) {
            Currency currency = optionalCurrency.get();
            currency.setName(currencyDto.getName());
            currency.setActive(currencyDto.isActive());
            currencyRepository.save(currency);
            return new Result(true,"Currency updated successfully");
        }
        return new Result(false,"Currency not found");
    }

    public Result deleteCurrencyById(Integer id) {
        currencyRepository.deleteById(id);
        return new Result(true,"Currency deleted Successfully");
    }
}
