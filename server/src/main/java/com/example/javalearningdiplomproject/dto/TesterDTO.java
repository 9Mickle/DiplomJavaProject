package com.example.javalearningdiplomproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class TesterDTO {

    private Long id;
    private String question;
    private String rightAnswer;
    private List<String> answers;
}
