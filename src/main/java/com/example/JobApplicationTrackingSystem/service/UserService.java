package com.example.JobApplicationTrackingSystem.service;

import com.example.JobApplicationTrackingSystem.dto.UserCreateDTO;
import com.example.JobApplicationTrackingSystem.dto.UserDTO;
import com.example.JobApplicationTrackingSystem.exception.DuplicateUserException;
import com.example.JobApplicationTrackingSystem.exception.UserNotFoundException;
import com.example.JobApplicationTrackingSystem.mapper.MapperClass;
import com.example.JobApplicationTrackingSystem.model.Role;
import com.example.JobApplicationTrackingSystem.model.User;
import com.example.JobApplicationTrackingSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    MapperClass mapperClass;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, MapperClass mapperClass) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapperClass = mapperClass;
    }


    public UserDTO registerUser(UserCreateDTO userCreateDTO) throws DuplicateUserException {
        UserDTO userDTO = new UserDTO();
        String email = userCreateDTO.getEmail();
        String password = userCreateDTO.getPassword();
        if(userRepository.existsByEmail(email)){
            throw new DuplicateUserException("Another User already exists with this email: " + email);
        }
        else{
            User user = new User();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(Role.USER);
            userRepository.save(user);
            return mapperClass.toUserDTO(user);
        }

    }

    public UserDTO getUserByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("No User with the email " + email + " was found!") );
        return mapperClass.toUserDTO(user);
    }

    public UserDTO getUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("No User with the ID " + id + " was found!") );
        return mapperClass.toUserDTO(user);
    }

    public void deleteUserById(Long id) throws UserNotFoundException {
        userRepository.delete(userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("No User with the ID " + id + " was found!") ));
    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(u -> mapperClass.toUserDTO(u)).toList();
    }


}
