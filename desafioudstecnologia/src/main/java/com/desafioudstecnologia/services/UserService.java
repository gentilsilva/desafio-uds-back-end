package com.desafioudstecnologia.services;

import com.desafioudstecnologia.domain.user.User;
import com.desafioudstecnologia.dtos.user.UserDTO;
import com.desafioudstecnologia.dtos.user.UserForm;
import com.desafioudstecnologia.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDTO createUser(UserForm userForm) {
        User user = new User(userForm);
        this.userRepository.save(user);
        return new UserDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> userList = this.userRepository.findAll();
        return userList.stream().map(UserDTO::new).toList();
    }
}
