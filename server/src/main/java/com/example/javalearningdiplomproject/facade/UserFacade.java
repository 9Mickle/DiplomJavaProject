package com.example.javalearningdiplomproject.facade;

import com.example.javalearningdiplomproject.dto.UserDTO;
import com.example.javalearningdiplomproject.entity.User;
import org.springframework.stereotype.Component;

/**
 * Подготовка передачи пользователя на клиент и будем вызывать его на котроллере.
 */
@Component
public class UserFacade {

    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setLastname(user.getLastName());
        return userDTO;
    }
}
