package com.backbase.kalah.kalahassignment.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler({
      EmptyPitIdSelectedException.class,
      GameFinishedException.class,
      InvalidPitIdException.class,
      InvalidGameIdException.class,
      Throwable.class
  })
  protected ResponseEntity<ErrorDto> handleAllException(
      RuntimeException ex) {
    return new ResponseEntity<ErrorDto>(new ErrorDto(new Date(), ex.getMessage()), HttpStatus.BAD_REQUEST);
  }
}
