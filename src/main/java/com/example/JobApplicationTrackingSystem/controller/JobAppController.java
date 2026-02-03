package com.example.JobApplicationTrackingSystem.controller;

import com.example.JobApplicationTrackingSystem.dto.JobApplicationCreateDTO;
import com.example.JobApplicationTrackingSystem.dto.JobApplicationResponseDTO;
import com.example.JobApplicationTrackingSystem.exception.DuplicateJobApplicationException;
import com.example.JobApplicationTrackingSystem.exception.JobApplicationNotFoundException;
import com.example.JobApplicationTrackingSystem.service.JobApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobAppController {
    JobApplicationService jobApplicationService;

    public JobAppController(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping("/all")
    public List<JobApplicationResponseDTO> getAllJobs(){
        return jobApplicationService.getAllJobApplications();
    }

    @GetMapping("/all-user/{userId}")
    public List<JobApplicationResponseDTO> getAllJobsUser(@PathVariable Long userId){
        return jobApplicationService.getUserAllJob(userId);
    }
    @PostMapping("/{id}")
    public JobApplicationResponseDTO createJobApp(@PathVariable Long id, @RequestBody JobApplicationCreateDTO jobApplicationCreateDTO) throws DuplicateJobApplicationException {

        return jobApplicationService.createJobApplication(id,jobApplicationCreateDTO);
    }

    @GetMapping("/user/{userId}/job/{id}")
    public JobApplicationResponseDTO findById(@PathVariable Long userId, @PathVariable Long id) throws JobApplicationNotFoundException {
        return jobApplicationService.findById(id, userId);
    }

    @GetMapping("/count/{userId}")
    public Long countByUser(@PathVariable Long userId){
        return jobApplicationService.countByUser(userId);
    }
}
