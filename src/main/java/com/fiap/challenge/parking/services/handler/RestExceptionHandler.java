package com.fiap.challenge.parking.services.handler;

import com.fiap.challenge.parking.services.exceptions.BadRequestException;
import com.fiap.challenge.parking.services.exceptions.DatabaseException;
import com.fiap.challenge.parking.services.exceptions.Details.BadRequestExceptionDetails;
import com.fiap.challenge.parking.services.exceptions.Details.DatabaseExceptionDetails;
import com.fiap.challenge.parking.services.exceptions.Details.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Check the Documentation")
                        .details(e.getMessage())
                        .developerMessage(
                                Arrays.stream(e.getStackTrace()).findFirst().get().getFileName() + ":" +
                                Arrays.stream(e.getStackTrace()).findFirst().get().getLineNumber())
                        .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DatabaseException.class)
    protected ResponseEntity<DatabaseExceptionDetails> handleDatabaseException(DatabaseException e) {
        return new ResponseEntity<>(
                DatabaseExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .title("Database Exception, Check the Documentation")
                        .details(e.getMessage())
                        .developerMessage(
                                Arrays.stream(e.getStackTrace()).findFirst().get().getFileName() + ":" +
                                        Arrays.stream(e.getStackTrace()).findFirst().get().getLineNumber())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Invalid Fields")
                        .details("Check the field(s) error")
                        .developerMessage(ex.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }
}