package com.example.JobApplicationTrackingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationResponseDTO {

        private Long id;
        private String companyName;
        private String position;
        private String status;
        private String notes;
        private Long userId; // or some user info if needed

}
