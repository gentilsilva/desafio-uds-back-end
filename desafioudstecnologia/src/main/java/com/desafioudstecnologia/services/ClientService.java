package com.desafioudstecnologia.services;

import com.desafioudstecnologia.domain.client.Client;
import com.desafioudstecnologia.dtos.client.ClientDTO;
import com.desafioudstecnologia.dtos.client.ClientForm;
import com.desafioudstecnologia.exeptions.DuplicateRecordException;
import com.desafioudstecnologia.exeptions.InvalidDateArgumentException;
import com.desafioudstecnologia.exeptions.RecordNotFoundException;
import com.desafioudstecnologia.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientDTO createClient(ClientForm clientForm) {
        this.checkForDuplicate(clientForm);
        if(this.checkForValidDate(clientForm.birthDate())) throw new InvalidDateArgumentException(clientForm.birthDate());

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

    @Transactional
    public ClientDTO updateClient(ClientForm clientForm) {
        Client client = this.clientRepository.findClientByCpf(clientForm.cpf()).orElseThrow(() ->
                new RecordNotFoundException(clientForm.cpf()));
        client.update(clientForm);
            return new ClientDTO(client);
    }

    @Transactional
    public void deleteClient(String cpf) {
        Client client = this.clientRepository.findClientByCpf(cpf).orElseThrow(() -> new RecordNotFoundException(cpf));
        this.clientRepository.delete(client);
    }

    @Transactional(readOnly = true)
    public Client getClientByCpf(String cpf) {
        return this.clientRepository.findClientByCpf(cpf).orElseThrow(() -> new RecordNotFoundException(cpf));

    }

    public void checkForDuplicate(ClientForm clientForm) {
        Optional<Client> clientCpf = this.clientRepository.findClientByCpf(clientForm.cpf());
        Optional<Client> clientName = this.clientRepository.findClientByName(clientForm.name());

        if(clientCpf.isPresent()) {
            throw new DuplicateRecordException(clientForm.cpf());
        }

        if(clientName.isPresent()) {
            throw new DuplicateRecordException(clientForm.name());
        }
    }

    private LocalDate formatDate(String date) {
        DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, localDateFormatter);

        DateTimeFormatter dataBaseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String baseDate = localDate.format(dataBaseFormatter);

        return LocalDate.parse(baseDate);
    }

    public boolean checkForValidDate(String date) {
        if(date == null) return true;
        Pattern pattern = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4}");
        return !date.matches(pattern.pattern());
    }
}
