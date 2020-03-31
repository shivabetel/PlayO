package com.ss.playo.common.web;


import com.ss.playo.common.web.dto.ErrorDTO;
import com.ss.playo.common.web.exception.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
@PropertySource("classpath:playo-common-propeties.properties")
@ConfigurationProperties(prefix = "constraints")
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    private Map<String, String> sqlConstraints;


    public Map<String, String> getSqlConstraints() {
        return sqlConstraints;
    }

    public void setSqlConstraints(Map<String, String> sqlConstraints) {
        this.sqlConstraints = sqlConstraints;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setResponseCode(HttpStatus.BAD_REQUEST.toString());
        errorDTO.setResponseMessage("Something went wrong");
        if(ex.getCause() instanceof ConstraintViolationException){
            ConstraintViolationException constraintViolationException = (ConstraintViolationException)ex.getCause();
            Optional constraintName = Optional.of(constraintViolationException.getConstraintName());
            errorDTO.setResponseMessage(sqlConstraints.get(constraintName.orElse("default")));
        }else{
            errorDTO.setResponseMessage(sqlConstraints.get("default"));
        }
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(Exception ex, WebRequest request){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setResponseCode(HttpStatus.NOT_FOUND.toString());
        errorDTO.setResponseMessage(ex.getMessage());
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setResponseCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorDTO.setResponseMessage(ex.getMessage());
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
