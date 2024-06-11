package org.example.warehouse.service;

import org.example.warehouse.dto.ClientDto;
import org.example.warehouse.model.Client;
import org.example.warehouse.model.Result;
import org.example.warehouse.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
    }

    public Result createClient(ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        clientRepository.save(client);
        return new Result(true,"Client created successfully");
    }

    public Result updateClient(ClientDto clientDto,Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isPresent()){
            Client client = optionalClient.get();
            client.setName(clientDto.getName());
            client.setPhoneNumber(clientDto.getPhoneNumber());
            clientRepository.save(client);
            return new Result(true,"Client Updated successfully");
        }
        return  new Result(false,"Client Not found");
    }

    public Result deleteClientById(Integer id){
        clientRepository.deleteById(id);
        return new Result(true,"Client deleted successfully");
    }
}