package com.example.javalearningdiplomproject.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Передача пользователя на клиент.
 */
@Data
public class UserDTO {

    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String lastname;
}
