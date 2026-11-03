package com.deployhub.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidation(MethodArgumentNotValidException ex){
       HashMap<String ,String> errors = new HashMap<>();
       ex.getBindingResult().getFieldErrors().forEach(error -> {
           errors.put(
                   error.getField(),
                   error.getDefaultMessage()
           );
       });
       return ResponseEntity.badRequest().body(errors);
   }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExists(
            UserAlreadyExistsException ex) {

        return ResponseEntity
                .status(409)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(
            ResourceNotFoundException ex) {

        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)  //if no other exceptions match
    public ResponseEntity<String> handleGeneralException(Exception ex) {

        return ResponseEntity
                .status(500)
                .body("Something went wrong");
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(
            ResponseStatusException ex) {

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(ex.getReason());
    }
}
