package com.cdleldt;

import com.cdleldt.course.courseContent.CourseContentNotFoundException;
import com.cdleldt.course.courseOrder.NotPayedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatusCode status, WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", new Date());
    body.put("status", status.value());

    List<String> errors = new LinkedList<>();
    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      errors.add(fieldError.getDefaultMessage());
    }
    body.put("errors", errors);
    return new ResponseEntity<>(body, headers, status);
  }

  @ExceptionHandler({NotPayedException.class})
  private ResponseEntity<?> handler(NotPayedException e) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
  }

  @ExceptionHandler({CourseContentNotFoundException.class})
  private ResponseEntity<?> handler(CourseContentNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }
}