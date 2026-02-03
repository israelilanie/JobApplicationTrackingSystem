package com.example.JobApplicationTrackingSystem.service;

import com.example.JobApplicationTrackingSystem.dto.JobApplicationCreateDTO;
import com.example.JobApplicationTrackingSystem.dto.JobApplicationResponseDTO;
import com.example.JobApplicationTrackingSystem.exception.DuplicateJobApplicationException;
import com.example.JobApplicationTrackingSystem.exception.JobApplicationNotFoundException;
import com.example.JobApplicationTrackingSystem.mapper.MapperClass;
import com.example.JobApplicationTrackingSystem.model.ApplicationStatus;
import com.example.JobApplicationTrackingSystem.model.JobApplication;
import com.example.JobApplicationTrackingSystem.model.User;
import com.example.JobApplicationTrackingSystem.repository.JobApplicationRepository;
import com.example.JobApplicationTrackingSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JobApplicationService {

    JobApplicationRepository jobApplicationRepository;
    UserRepository userRepository;
    MapperClass mapperClass;


    public JobApplicationService(JobApplicationRepository jobApplicationRepository, UserRepository userRepository, MapperClass mapperClass) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.userRepository = userRepository;
        this.mapperClass = mapperClass;
    }

    public JobApplicationResponseDTO createJobApplication(Long id, JobApplicationCreateDTO jobApplicationCreateDTO) throws DuplicateJobApplicationException {
        JobApplication jobApplication = new JobApplication();
        User user = userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found with ID:" + id));
        if(jobApplicationRepository.existsByCompanyNameAndUser(jobApplicationCreateDTO.getCompanyName(), user)) {
            throw new DuplicateJobApplicationException("Same Job Application was found! Cant Applied twice the same Job");
        }

        jobApplication.setUser(user);
        jobApplication.setCompanyName(jobApplicationCreateDTO.getCompanyName());
        jobApplication.setPosition(jobApplicationCreateDTO.getPosition());
        jobApplication.setNotes(jobApplicationCreateDTO.getNotes());
        jobApplication.setStatus(ApplicationStatus.APPLIED);
        jobApplication.setAppliedDate(LocalDateTime.now());
        //user.getJobApplications().add(jobApplication);
        jobApplicationRepository.save(jobApplication);

        return mapperClass.toJobAppDTO(jobApplication);
    }

    public List<JobApplicationResponseDTO> getAllJobApplications(){
        return jobApplicationRepository.findAll().stream().map(u->mapperClass.toJobAppDTO(u)).toList();
    }

    public List<JobApplicationResponseDTO> getUserAllJob(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->new UsernameNotFoundException("User not found with ID:" + userId));
        return jobApplicationRepository.findAllByUser(user).stream().map(u->mapperClass.toJobAppDTO(u)).toList();
    }

    public List<JobApplicationResponseDTO> findAllByUserAndStatus(User user, ApplicationStatus status){
        return jobApplicationRepository.findAllByUserAndStatus(user,status).stream().map(u->mapperClass.toJobAppDTO(u)).toList();
    }

    public JobApplicationResponseDTO findById(Long id, Long userId) throws JobApplicationNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()->new UsernameNotFoundException("User not found with ID:" + userId));
        JobApplication jobApplication = jobApplicationRepository.findByIdAndUser(id,user).orElseThrow(()-> new JobApplicationNotFoundException("No Job Application was found with ID: " + id));
        return mapperClass.toJobAppDTO(jobApplication);
    }
    public boolean existsByCompanyNameAndUser(String companyName, User user){
        return jobApplicationRepository.existsByCompanyNameAndUser(companyName, user);
    }

    public Long countByUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->new UsernameNotFoundException("User not found with ID:" + userId));
        return jobApplicationRepository.countByUser(user);
    }

}
