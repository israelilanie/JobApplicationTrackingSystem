package com.example.JobApplicationTrackingSystem.repository;

import com.example.JobApplicationTrackingSystem.model.ApplicationStatus;
import com.example.JobApplicationTrackingSystem.model.JobApplication;
import com.example.JobApplicationTrackingSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {
    List<JobApplication> findAllByUser(User user);
    List<JobApplication> findAllByUserAndStatus(User user, ApplicationStatus status);
    Optional<JobApplication> findByIdAndUser(Long id, User user);
    boolean existsByCompanyNameAndUser(String companyName, User user);
    Long countByUser(User user);
}
