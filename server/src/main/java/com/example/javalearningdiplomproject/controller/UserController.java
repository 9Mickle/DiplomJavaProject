package com.example.javalearningdiplomproject.controller;

import com.example.javalearningdiplomproject.dto.UserDTO;
import com.example.javalearningdiplomproject.entity.User;
import com.example.javalearningdiplomproject.facade.UserFacade;
import com.example.javalearningdiplomproject.services.UserService;
import com.example.javalearningdiplomproject.validation.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

import static com.example.javalearningdiplomproject.entity.enums.ERole.ROLE_ADMIN;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserFacade userFacade;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    /**
     * Метод, возвращающий авторизированного пользователя.
     * @param principal хранит данные об авторизированном пользователе.
     * @return пользователь.
     */
    @GetMapping("/")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal){
        User user = userService.getCurrentUser(principal);
        UserDTO userDTO = userFacade.userToUserDTO(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    /**
     * Метод, возвращающий пользователя по его id.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable("userId") String userId) {
        User user = userService.getUserById(Long.parseLong(userId));
        UserDTO userDTO = userFacade.userToUserDTO(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    /**
     * Метод, обновляющий данные пользователя.
     */
    @PostMapping("/update")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO userDTO, BindingResult result, Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(result);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        User user = userService.updateUser(userDTO, principal);

        UserDTO userUpdated = userFacade.userToUserDTO(user);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @GetMapping("/{userId}/role")
    public Boolean getUserRole(@PathVariable("userId") String userId) {
        return userService.getUserById(Long.parseLong(userId)).getRoles().contains(ROLE_ADMIN);
    }
}
