package com.example.JobApplicationTrackingSystem.controller;

import com.example.JobApplicationTrackingSystem.dto.JobApplicationCreateDTO;
import com.example.JobApplicationTrackingSystem.dto.JobApplicationResponseDTO;
import com.example.JobApplicationTrackingSystem.exception.DuplicateJobApplicationException;
import com.example.JobApplicationTrackingSystem.exception.JobApplicationNotFoundException;
import com.example.JobApplicationTrackingSystem.model.JobApplication;
import com.example.JobApplicationTrackingSystem.model.User;
import com.example.JobApplicationTrackingSystem.service.JobApplicationService;
import com.example.JobApplicationTrackingSystem.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobAppController {
    UserService userService;
    JobApplicationService jobApplicationService;

    public JobAppController(UserService userService, JobApplicationService jobApplicationService) {
        this.userService = userService;
        this.jobApplicationService = jobApplicationService;
    }

    @GetMapping("/all")
    public List<JobApplicationResponseDTO> getAllJobs(){
        return jobApplicationService.getAllJobApplications();
    }

    @GetMapping("/all-user")
    public List<JobApplicationResponseDTO> getAllJobsUser(@RequestBody User user){
        return jobApplicationService.getUserAllJob(user);
    }
    @PostMapping("/{id}")
    public JobApplicationResponseDTO createJobApp(@PathVariable Long id, @RequestBody JobApplicationCreateDTO jobApplicationCreateDTO) throws DuplicateJobApplicationException {

        return jobApplicationService.createJobApplication(id,jobApplicationCreateDTO);
    }

    @GetMapping("/userjob/{id}")
    public JobApplicationResponseDTO findById(@PathVariable Long id, @RequestBody User user) throws JobApplicationNotFoundException {
        return jobApplicationService.findById(id,user);
    }

    @GetMapping("/count")
    public Long countByUser(@RequestBody User user){
        return jobApplicationService.countByUser(user);
    }
}
