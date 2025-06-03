package com.proyecto.reusa.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageReadableException(HttpMessageNotReadableException e){
        Map<String, String> error = new HashMap<>();
        error.put("error", "formato de dato incorrecto.");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e){
        Map<String, String> errores = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, String> errores = new HashMap<>();

        e.getConstraintViolations().forEach(violation -> {
            String campo = violation.getPropertyPath().toString();
            String mensaje = violation.getMessage();
            errores.put(campo, mensaje);
        });

        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Tipo de dato inválido para el parámetro: " + ex.getName());
        error.put("valor", ex.getValue() != null ? ex.getValue().toString() : null);
        error.put("tipoEsperado", ex.getRequiredType().getSimpleName());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(CustomException msg){
        Map<String, String> error = new HashMap<>();
        error.put("error", msg.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Map<String, String>> handleMultipartException(MultipartException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Error al procesar la subida del archivo. Detalle: " + ex.getMessage());
        // Imprime el stack trace completo para depuración
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Este es el handler general, siempre colócalo al final para que otros más específicos tengan prioridad
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllUnhandledExceptions(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Ocurrió un error inesperado en el servidor.");
        error.put("message", ex.getMessage()); // Incluye el mensaje de la excepción
        // Imprime el stack trace completo para depuración
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
