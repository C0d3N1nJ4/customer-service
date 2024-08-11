package com.naiomi.exceptions;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.HttpResponse;

public class CustomerExceptionHandler  implements ExceptionHandler<CustomerNotFoundException, HttpResponse>{
    @Override
    public HttpResponse handle(HttpRequest request, CustomerNotFoundException exception) {
        return HttpResponse.notFound(exception.getMessage());
    }
}
