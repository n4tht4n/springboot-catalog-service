package com.polarbookshop.catalogservice.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.validation.FieldError;

import com.polarbookshop.catalogservice.domain.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.domain.BookNotFoundException;

/**
 * An exception handler which is especially required due to automatic bean
 * validation.
 *
 * The validation rules used in `Book` would throw an
 * `MethodArgumentNotValidException` which makes now sense in case of a REST
 * API. Using this `@RestControllerAdvice` annotated class, we can return
 * other status codes when sending back the response.
 */
@RestControllerAdvice
public class BookControllerAdvice {
  @ExceptionHandler(BookNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String bookNotFoundHandler(BookNotFoundException ex) {
    return ex.getMessage();
  }

  @ExceptionHandler(BookAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  String bookAlreadyExistsHandler(BookAlreadyExistsException ex) {
    return ex.getMessage();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    var errors = new HashMap<String, String>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }
}