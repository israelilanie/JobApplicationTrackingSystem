package com.example.JobApplicationTrackingSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> UserNotFoundException(UserNotFoundException userNotFoundException, WebRequest webRequest){
        ErrorResponse errorResponse =
                new ErrorResponse(LocalDateTime.now(),userNotFoundException.getMessage(),webRequest.getDescription(false),"USER NOT FOUND");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(JobApplicationNotFoundException.class)
    public ResponseEntity<ErrorResponse> JobApplicationNotFoundException(JobApplicationNotFoundException jobApplicationNotFoundException, WebRequest webRequest){
        ErrorResponse errorResponse =
                new ErrorResponse(LocalDateTime.now(),jobApplicationNotFoundException.getMessage(),webRequest.getDescription(false),"JOB APPLICATION NOT FOUND");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<ErrorResponse> duplicateUserException(DuplicateUserException duplicateUserException, WebRequest webRequest){
        ErrorResponse errorResponse =
                new ErrorResponse(LocalDateTime.now(),duplicateUserException.getMessage(),webRequest.getDescription(false),"USER_ALREADY_EXISTS");
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DuplicateJobApplicationException.class)
    public ResponseEntity<ErrorResponse> duplicateJobApplicationException(DuplicateJobApplicationException duplicateJobApplicationException, WebRequest webRequest){
        ErrorResponse errorResponse =
                new ErrorResponse(LocalDateTime.now(),duplicateJobApplicationException.getMessage(),webRequest.getDescription(false),"JOB_ALREADY_EXISTS");
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ErrorResponse> unauthorizedAccessException(UnauthorizedAccessException unauthorizedAccessException, WebRequest webRequest){
        ErrorResponse errorResponse =
                new ErrorResponse(LocalDateTime.now(),unauthorizedAccessException.getMessage(),webRequest.getDescription(false),"ACCESS_DENIED");
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> invalidRequestException(InvalidRequestException invalidRequestException, WebRequest webRequest){
        ErrorResponse errorResponse =
                new ErrorResponse(LocalDateTime.now(),invalidRequestException.getMessage(),webRequest.getDescription(false),"INVALID_REQUEST");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> authenticationException(AuthenticationException authenticationException, WebRequest webRequest){
        ErrorResponse errorResponse =
                new ErrorResponse(LocalDateTime.now(),authenticationException.getMessage(),webRequest.getDescription(false),"AUTHENTICATION_FAILED");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

}
