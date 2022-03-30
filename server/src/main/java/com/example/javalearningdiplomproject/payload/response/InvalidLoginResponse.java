package com.example.javalearningdiplomproject.payload.response;

import lombok.Getter;

/**
 * Когда будет возникать ошибка 401, мы будем создавать этот объект на Angular клиенту.
 */
@Getter
public class InvalidLoginResponse {

    private String username;
    private String password;

    public InvalidLoginResponse() {
        this.username = "Invalid Username";
        this.password = "Invalid Password";
    }
}
