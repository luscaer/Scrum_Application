package com.luscaer.Scrum_application.infra;

import com.luscaer.Scrum_application.exception.EntityNotFoundException;
import com.luscaer.Scrum_application.exception.InvalidRequestException;
import com.luscaer.Scrum_application.exception.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RestErrorMessage> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(restErrorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<RestErrorMessage> handleInvalidRequestException(InvalidRequestException exception, WebRequest request) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(restErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorMessage> handleGlobalException(WebRequest request) {
        RestErrorMessage restErrorMessage = new RestErrorMessage("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(restErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
