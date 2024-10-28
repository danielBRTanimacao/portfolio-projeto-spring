package com.springweb.spring_web.resource.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.springweb.spring_web.services.exceptions.ResourceNotFoundException;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    // Intercepta ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, WebRequest request) {
        String error = "Recurso não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND; // Status 404
        StandardError err = new StandardError(
                Instant.now(), // Timestamp atual
                status.value(), // Código do status HTTP
                error, // Mensagem do erro
                e.getMessage(), // Mensagem específica da exceção
                request.getDescription(false).replace("uri=", "") // Caminho da requisição
        );
        return ResponseEntity.status(status).body(err); // Retorna a resposta com status 404 e corpo personalizado
    }
}
