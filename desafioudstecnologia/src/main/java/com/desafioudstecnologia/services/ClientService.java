package com.desafioudstecnologia.services;

import com.desafioudstecnologia.domain.client.Client;
import com.desafioudstecnologia.dtos.client.ClientDTO;
import com.desafioudstecnologia.dtos.client.ClientForm;
import com.desafioudstecnologia.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientDTO createClient(ClientForm clientForm) {
        Client client = new Client(clientForm);
        this.clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public List<ClientDTO> getAllClients() {
        List<Client> clientList = this.clientRepository.findAll();
        return clientList.stream().map(ClientDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<ClientDTO> getAllClientsByParams(String name, String cpf, String birthDate) {
        LocalDate date = null;
        if(!(birthDate == null)) {
            date = formatDate(birthDate);
        }
        List<Client> clientList = this.clientRepository.findAllUsersByNameOrCpfOrBirthDate(name, cpf, date);
        return clientList.stream().map(ClientDTO::new).toList();
    }

    private LocalDate formatDate(String date) {
        DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, localDateFormatter);

        DateTimeFormatter dataBaseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String databaseFormat = localDate.format(dataBaseFormatter);

        return LocalDate.parse(databaseFormat);
    }

    @Transactional
    public ClientDTO updateClient(ClientForm clientForm) throws Exception {
        Optional<Client> client = this.clientRepository.findClientByCpf(clientForm.cpf());
        if(client.isPresent()) {
            client.get().update(clientForm);
            return new ClientDTO(client.get());
        }
        throw new Exception("Cliente não encontrado");
    }
}
