package com.example.JobApplicationTrackingSystem.dto;

import com.example.JobApplicationTrackingSystem.model.ApplicationStatus;
import com.example.JobApplicationTrackingSystem.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationCreateDTO {
    private String companyName;
    private String position;
    private String status ;
    private String notes;
    private User user;
}
