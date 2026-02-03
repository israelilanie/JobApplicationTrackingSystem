package com.example.JobApplicationTrackingSystem.controller;

import com.example.JobApplicationTrackingSystem.dto.UserCreateDTO;
import com.example.JobApplicationTrackingSystem.dto.UserDTO;
import com.example.JobApplicationTrackingSystem.exception.DuplicateUserException;
import com.example.JobApplicationTrackingSystem.exception.UserNotFoundException;
import com.example.JobApplicationTrackingSystem.model.User;
import com.example.JobApplicationTrackingSystem.service.JobApplicationService;
import com.example.JobApplicationTrackingSystem.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    UserService userService;
    JobApplicationService jobApplicationService;

    public UserController(UserService userService, JobApplicationService jobApplicationService) {
        this.userService = userService;
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUser(){
        return userService.getAllUsers();
    }
    @GetMapping("/email/{email}")
    public UserDTO getUserByEmail(@PathVariable String email) throws UserNotFoundException {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/id/{id}")
    public UserDTO getUserByEmail(@PathVariable Long id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(Long id) throws UserNotFoundException {
        userService.deleteUserById(id);
    }

    @PostMapping("/{email}/{password}")
    public UserDTO createUser(@RequestBody UserCreateDTO userCreateDTO) throws DuplicateUserException {
        return userService.registerUser(userCreateDTO);
    }

}
