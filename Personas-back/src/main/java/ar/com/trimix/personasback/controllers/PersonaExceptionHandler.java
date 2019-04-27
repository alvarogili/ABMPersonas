package ar.com.trimix.personasback.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Controlador de excepciones
 * Author: agili
 * Date: 27/04/19
 */
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Error> handleBadRequest(RuntimeException ex) {
        Error error = new Error();
        final String message = ex.getMessage();
        error.setMessage(message);
        error.setCause(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
