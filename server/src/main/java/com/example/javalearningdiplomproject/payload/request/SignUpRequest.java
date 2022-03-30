package com.example.javalearningdiplomproject.payload.request;

import com.example.javalearningdiplomproject.annotation.PasswordMatches;
import com.example.javalearningdiplomproject.annotation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Этот объект будем использовать когда будем пытаться зарегистрировать нового пользователя.
 */
@Data
@PasswordMatches // Кастомная аннотация проверки правильности паролей password и confirmPassword.
public class SignUpRequest {

    @Email(message = "It should be email format")
    @NotBlank(message = "User email is required")
    @ValidEmail // Кастомная аннотация проверки email.
    private String email;
    @NotEmpty(message = "Please enter your name")
    private String lastname;
    @NotEmpty(message = "Please enter your username")
    private String username;
    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    private String password;
    private String confirmPassword;
}
