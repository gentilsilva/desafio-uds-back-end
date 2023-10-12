package com.desafioudstecnologia.services;

import com.desafioudstecnologia.domain.user.User;
import com.desafioudstecnologia.dtos.user.UserDTO;
import com.desafioudstecnologia.dtos.user.UserForm;
import com.desafioudstecnologia.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        List<User> userList = this.userRepository.findAll();
        return userList.stream().map(UserDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsersByParams(String name, String cpf, String birthDate) {
        LocalDate date = null;
        if(!(birthDate == null)) {
            date = formatDate(birthDate);
        }
        List<User> userList = this.userRepository.findAllUsersByNameOrCpfOrBirthDate(name, cpf, date);
        return userList.stream().map(UserDTO::new).toList();
    }

    private LocalDate formatDate(String date) {
        DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, localDateFormatter);

        DateTimeFormatter dataBaseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String databaseFormat = localDate.format(dataBaseFormatter);

        return LocalDate.parse(databaseFormat);
    }
}
