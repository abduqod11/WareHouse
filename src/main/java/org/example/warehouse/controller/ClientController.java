package org.example.warehouse.controller;

import org.example.warehouse.dto.ClientDto;
import org.example.warehouse.model.Client;
import org.example.warehouse.model.Result;
import org.example.warehouse.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Client")

public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public Optional<Client> getClientById(@PathVariable Integer id){
        return clientService.getClientById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result addClient(@RequestBody ClientDto clientDto){
        return clientService.createClient(clientDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result updateClient(@RequestBody ClientDto clientDto, @PathVariable Integer id){
        return clientService.updateClient(clientDto,id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    public Result deleteClientById(@PathVariable Integer id){
        return clientService.deleteClientById(id);
    }
}