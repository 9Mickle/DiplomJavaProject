package com.example.javalearningdiplomproject.controller;

import com.example.javalearningdiplomproject.entity.User;
import com.example.javalearningdiplomproject.payload.request.LoginRequest;
import com.example.javalearningdiplomproject.payload.request.SignUpRequest;
import com.example.javalearningdiplomproject.payload.response.JWTTokenSuccessResponse;
import com.example.javalearningdiplomproject.payload.response.MessageResponse;
import com.example.javalearningdiplomproject.security.JWTTokenProvider;
import com.example.javalearningdiplomproject.security.SecurityConstants;
import com.example.javalearningdiplomproject.services.UserService;
import com.example.javalearningdiplomproject.validation.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Контроллер, отвечающий за авторизацию пользователя.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;
    @Autowired
    private UserService userService;

    /**
     * Метод отсылающий в базу данных данные зрагестрированного пользователя и позволяющий пользователю авторизироваться.
     */
    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        // Если error не пустой и в нём есть ошибки, то возвращаем эти ошибки.
        if (!ObjectUtils.isEmpty(errors)) { return errors; }

        // Если нет никаких ошибок с объекта LoginRequest, то мы генерируем токен, задаем туда все данные пользователя
        // и передаем его на клиент - Angular.
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }

    /**
     *
     * @param signUpRequest когда пользователь пытается зарегистрироваться он передает этот объект
     *                      с полями, которые должны быть при регистрации.
     */
    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignUpRequest signUpRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        // Если error не пустой и в нём есть ошибки, то возвращаем эти ошибки.
        if (!ObjectUtils.isEmpty(errors)) { return errors; }

        // Если нет никаких ошибок с объекта SignUpRequest (которые могут быть при email или пароли могут не совпадать),
        // то создаем нового пользователя и возвращаем статус 200 и сообщение.
        userService.createNewUser(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
}
