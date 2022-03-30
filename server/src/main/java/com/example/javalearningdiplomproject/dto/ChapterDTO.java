package com.example.javalearningdiplomproject.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ChapterDTO {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String title;
    private Integer number;
}
