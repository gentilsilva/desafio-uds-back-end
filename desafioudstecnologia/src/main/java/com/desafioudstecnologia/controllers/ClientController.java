package com.desafioudstecnologia.controllers;

import com.desafioudstecnologia.dtos.client.ClientDTO;
import com.desafioudstecnologia.dtos.client.ClientForm;
import com.desafioudstecnologia.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody @Valid ClientForm clientForm, UriComponentsBuilder uriComponentsBuilder) {
        ClientDTO clientDTO = this.clientService.createClient(clientForm);
        URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(clientDTO.id()).toUri();
        return ResponseEntity.created(uri).body(clientDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clientDTOList = this.clientService.getAllClients();
        return ResponseEntity.ok().body(clientDTOList);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClientDTO>> getAllClientsByParams(@RequestParam(required = false) String name, @RequestParam(required = false) String cpf,
                                                               @RequestParam(required = false) String birthDate) {
        List<ClientDTO> clientDTOList = this.clientService.getAllClientsByParams(name, cpf, birthDate);
        return ResponseEntity.ok().body(clientDTOList);
    }

    @PutMapping
    public ResponseEntity<ClientDTO> updateClient(@RequestBody @Valid ClientForm clientForm) {
        ClientDTO clientDTO = this.clientService.updateClient(clientForm);
        return ResponseEntity.ok().body(clientDTO);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<?> deleteClient(@PathVariable String cpf) {
        this.clientService.deleteClient(cpf);
        return ResponseEntity.noContent().build();
    }

}
