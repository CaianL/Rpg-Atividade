package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class Responses{
    public static ResponseEntity<Object> Sucesso(Object data){
        return ResponseEntity.ok(Map.of("Successo!", true, "data", data));
    }

    public static ResponseEntity<Object> Falha(String message, HttpStatus status){
        return ResponseEntity.status(status).body(Map.of("success", false, "error", message));
    }
}
