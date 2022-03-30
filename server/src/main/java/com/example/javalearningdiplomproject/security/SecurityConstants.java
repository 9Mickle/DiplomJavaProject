package com.example.javalearningdiplomproject.security;

public class SecurityConstants {

    /**
     * По этому адресу будет происходить авторизация пользователя, после ** любой url.
     */
    public static final String SIGN_UP_URLS = "/api/auth/*";

    /**
     * Требуется для генерации JWT.
     */
    public static final String SECRET = "SecretKeyGenJWT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
    public static final long EXPIRATION_TIME = 600_000_000; // Через t минут веб токен перестанет действовать и произойдет logout.
}