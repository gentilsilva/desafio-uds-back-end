package com.desafioudstecnologia.controllers;

import com.desafioudstecnologia.dtos.user.UserDTO;
import com.desafioudstecnologia.dtos.user.UserForm;
import com.desafioudstecnologia.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriComponentsBuilder) {
        UserDTO userDTO = this.userService.createUser(userForm);
        URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(userDTO.id()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOList = this.userService.getAllUsers();
        return ResponseEntity.ok().body(userDTOList);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> getAllUsersByParams(@RequestParam(required = false) String name, @RequestParam(required = false) String cpf,
                                                             @RequestParam(required = false) String birthDate) {
        List<UserDTO> userDTOList = this.userService.getAllUsersByParams(name, cpf, birthDate);
        return ResponseEntity.ok().body(userDTOList);
    }

}
