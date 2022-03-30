package com.example.javalearningdiplomproject.facade;

import com.example.javalearningdiplomproject.dto.TesterDTO;
import com.example.javalearningdiplomproject.entity.Tester;
import org.springframework.stereotype.Component;

@Component
public class TesterFacade {

    public TesterDTO testerToTesterDTO(Tester tester) {
        TesterDTO testerDTO = new TesterDTO();
        testerDTO.setId(tester.getId());
        testerDTO.setQuestion(tester.getQuestion());
        testerDTO.setRightAnswer(tester.getRightAnswer());
        testerDTO.setAnswers(tester.getAnswers());
        return testerDTO;
    }
}
