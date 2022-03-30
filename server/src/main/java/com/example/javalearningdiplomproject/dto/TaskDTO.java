package com.example.javalearningdiplomproject.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TaskDTO {

    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
}
