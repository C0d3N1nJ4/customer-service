package com.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    ProblemDetail handleCustomerNotFoundException(CustomerNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Customer not found");
        return problemDetail;
    }

    @ExceptionHandler(StatusNotFoundException.class)
    ProblemDetail handleStatusNotFoundException(StatusNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Status not found");
        return problemDetail;
    }

    @ExceptionHandler(AddressNotFoundException.class)
    ProblemDetail handleAddressNotFoundException(AddressNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Address not found");
        return problemDetail;
    }

    @ExceptionHandler(ContactNotFoundException.class)
    ProblemDetail handleContactNotFoundException(ContactNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Contact not found");
        return problemDetail;
    }

}
