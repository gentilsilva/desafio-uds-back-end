package com.desafioudstecnologia.controllers;

import com.desafioudstecnologia.dtos.user.UserDTO;
import com.desafioudstecnologia.dtos.user.UserForm;
import com.desafioudstecnologia.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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


}
