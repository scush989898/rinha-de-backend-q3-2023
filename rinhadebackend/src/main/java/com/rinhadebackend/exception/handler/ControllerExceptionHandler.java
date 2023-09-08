package com.rinhadebackend.exception.handler;

import com.rinhadebackend.exception.custom.EntityNotFoundException;
import jakarta.servlet.ServletException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail threatDuplicateEntry(DataIntegrityViolationException e) {

        return ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY,
                e.getLocalizedMessage());
    }


    @ExceptionHandler({IllegalArgumentException.class, HttpMessageConversionException.class})
    public ProblemDetail threatInvalidPropertiesFormat(RuntimeException e) {

        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                e.getLocalizedMessage());
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail threatEntityNotFound(RuntimeException e) {

        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                e.getLocalizedMessage());
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public ProblemDetail threatEntityNotFound(ServletException e) {

        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                e.getLocalizedMessage());
    }


    @ExceptionHandler(Exception.class)
    public ProblemDetail threatEntityNotFound(Exception e) {

        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
                e.getLocalizedMessage());
    }

}
