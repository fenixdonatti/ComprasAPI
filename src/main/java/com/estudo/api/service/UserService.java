package com.estudo.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.estudo.api.dto.user.UserCreateDTO;
import com.estudo.api.dto.user.UserDataDTO;
import com.estudo.api.model.User;
import com.estudo.api.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDataDTO saveUser(UserCreateDTO userCreateDTO) {
        long currentTimestamp = System.currentTimeMillis();
        User user = new User();
        
        user.setName(userCreateDTO.getName());
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setCreatedAt(currentTimestamp);
        user.setUpdatedAt(currentTimestamp);

        User savedUser = userRepository.save(user);
        return new UserDataDTO(savedUser.getName(), savedUser.getEmail());
    }

    public UserDataDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return new UserDataDTO(user.getName(), user.getEmail());
        }
        return null;
    }
}
