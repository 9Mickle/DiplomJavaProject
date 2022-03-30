package com.example.javalearningdiplomproject.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Валидатор, отвечающий за получение ошибок с сервера.
 */
@Service
public class ResponseErrorValidation {

    // Все прияходящие ошибки находятся в BindingResult
    public ResponseEntity<Object> mapValidationService(BindingResult result) {
        // Если есть ошибки
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            // Если коллекция с ошибками не пуста
            if (!CollectionUtils.isEmpty(result.getAllErrors())) {
                for (ObjectError error : result.getAllErrors()) {
                    // Кладем в мапу код ошибки и сообщение.
                    errorMap.put(error.getCode(), error.getDefaultMessage());
                }
            }

            // Если ошибок не было
            for (FieldError error : result.getFieldErrors()) {
                // Кладем в мапу в каком именно поле была ошибка и сообщение.
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            // Если были ошибки возвращаем ошибку 400.
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
         }
        // Если нет ошибок возвращаем null.
        return null;
    }
}
