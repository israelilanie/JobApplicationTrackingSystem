package com.example.JobApplicationTrackingSystem.mapper;

import com.example.JobApplicationTrackingSystem.dto.JobApplicationResponseDTO;
import com.example.JobApplicationTrackingSystem.dto.UserDTO;
import com.example.JobApplicationTrackingSystem.model.JobApplication;
import com.example.JobApplicationTrackingSystem.model.User;
import org.springframework.stereotype.Component;

@Component
public class MapperClass {
    public UserDTO toUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole().toString());
        return userDTO;
    }

    public JobApplicationResponseDTO toJobAppDTO(JobApplication jobApplication){
        JobApplicationResponseDTO jobApplicationResponseDTO = new JobApplicationResponseDTO();
        jobApplicationResponseDTO.setId(jobApplication.getId());
        jobApplicationResponseDTO.setNotes(jobApplication.getNotes());
        jobApplicationResponseDTO.setStatus(jobApplication.getStatus().toString());
        jobApplicationResponseDTO.setUserId(jobApplication.getUser().getId());
        jobApplicationResponseDTO.setPosition(jobApplication.getPosition());
        jobApplicationResponseDTO.setCompanyName(jobApplication.getCompanyName());
        return jobApplicationResponseDTO;
    }
}
