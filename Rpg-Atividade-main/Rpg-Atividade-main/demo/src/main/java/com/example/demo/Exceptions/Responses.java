package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Responses {

    //*Sucessos
    public static ResponseEntity<Object> Sucesso(String mensagem, Object data) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.OK.value());
        body.put("sucesso", true);
        body.put("mensagem", mensagem);
        body.put("data", data);

        return ResponseEntity.ok(body);
    }


    public static ResponseEntity<Object> Sucesso200(Object data) {
        return Sucesso("Operação realizada com sucesso", data);
    }

    //*Falhas
    public static ResponseEntity<Object> Falha(String mensagem, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("sucesso", false);
        body.put("mensagem", mensagem);
        body.put("erro", mensagem);

        return ResponseEntity.status(status).body(body);
    }


    public static ResponseEntity<Object> Falha400(String mensagem) {
        return Falha(mensagem, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Object> Respostapersonalizada(boolean sucesso, String mensagem, Object data, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("sucesso", sucesso);
        body.put("mensagem", mensagem);

        if (sucesso) {
            body.put("data", data);
        } else {
            body.put("erro", mensagem);
        }

        return ResponseEntity.status(status).body(body);
    }
}
