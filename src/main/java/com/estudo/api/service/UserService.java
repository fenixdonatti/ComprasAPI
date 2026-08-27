package com.estudo.api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.estudo.api.dto.user.UserCreateDTO;
import com.estudo.api.dto.user.UserDataDTO;
import com.estudo.api.dto.user.UserUpdateDTO;
import com.estudo.api.error.user.UserAlreadyExistsException;
import com.estudo.api.error.user.UserCreateException;
import com.estudo.api.error.user.UserDeleteException;
import com.estudo.api.error.user.UserNotFoundException;
import com.estudo.api.error.user.UserUpdateException;
import com.estudo.api.model.User;
import com.estudo.api.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDataDTO saveUser(UserCreateDTO userCreateDTO)
            throws UserAlreadyExistsException, UserCreateException {
        if (userRepository.findByEmail(userCreateDTO.getEmail()) != null) {
            throw new UserAlreadyExistsException("User email already exists");
        }

        long currentTimestamp = System.currentTimeMillis();
        User user = new User();

        user.setName(userCreateDTO.getName());
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setCreatedAt(currentTimestamp);
        user.setUpdatedAt(currentTimestamp);

        try {
            User savedUser = userRepository.save(user);
            return new UserDataDTO(savedUser.getName(), savedUser.getEmail());
        } catch (DataIntegrityViolationException exception) {
            throw new UserAlreadyExistsException("User email already exists");
        } catch (RuntimeException exception) {
            throw new UserCreateException("Could not create user", exception);
        }
    }

    public UserDataDTO getUserByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return new UserDataDTO(user.getName(), user.getEmail());
    }

    public UserDataDTO getUserByUUID(String uuid) throws UserNotFoundException {
        return userRepository.findById(UUID.fromString(uuid))
            .map(user -> new UserDataDTO(user.getName(), user.getEmail()))
            .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public UserUpdateDTO updateUser(UserUpdateDTO data)
            throws UserNotFoundException, UserAlreadyExistsException, UserUpdateException {
        Optional<User> dbUser = userRepository.findById(data.getUuid());
        if (dbUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = dbUser.get();
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setUpdatedAt(System.currentTimeMillis());

        try {
            userRepository.save(user);
            return data;
        } catch (DataIntegrityViolationException exception) {
            throw new UserAlreadyExistsException("User email already exists");
        } catch (RuntimeException exception) {
            throw new UserUpdateException("Could not update user", exception);
        }
    }

    public void deleteUser(UUID uuid) throws UserNotFoundException, UserDeleteException {
        if (!userRepository.existsById(uuid)) {
            throw new UserNotFoundException("User not found");
        }

        try {
            userRepository.deleteById(uuid);
        } catch (RuntimeException exception) {
            throw new UserDeleteException("Could not delete user", exception);
        }
    }
}
