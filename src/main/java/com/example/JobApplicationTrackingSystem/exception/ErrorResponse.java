package com.example.JobApplicationTrackingSystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    LocalDateTime timeStamp;
    String errorMessage;
    String errorDetails;
    String errorCode;
}
