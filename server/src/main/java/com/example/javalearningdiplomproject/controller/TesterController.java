package com.example.javalearningdiplomproject.controller;

import com.example.javalearningdiplomproject.dto.TesterDTO;
import com.example.javalearningdiplomproject.entity.Tester;
import com.example.javalearningdiplomproject.facade.TesterFacade;
import com.example.javalearningdiplomproject.services.TesterService;
import com.example.javalearningdiplomproject.validation.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tester")
@CrossOrigin
public class TesterController {

    @Autowired
    private TesterFacade testerFacade;
    @Autowired
    private TesterService testerService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping("/{taskId}/create")
    public ResponseEntity<Object> createTester(@Valid @RequestBody TesterDTO testerDTO, BindingResult bindingResult,
                                               @PathVariable("taskId") String taskId) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Tester tester = testerService.createTester(Long.parseLong(taskId), testerDTO);
        TesterDTO createdTesterDTO = testerFacade.testerToTesterDTO(tester);

        return new ResponseEntity<>(createdTesterDTO, HttpStatus.OK);
    }

    @GetMapping("/{taskId}/all")
    public ResponseEntity<List<TesterDTO>> getAllTesters(@PathVariable("taskId") String taskId) {
        List<TesterDTO> testerDTOList = testerService.getAllTesterByTaskId(Long.parseLong(taskId)).stream()
                .map(testerFacade::testerToTesterDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(testerDTOList, HttpStatus.OK);
    }

    @GetMapping("/{testerId}/answers")
    public ResponseEntity<List<String>> getAllAnswers(@PathVariable("testerId") String testerId) {
        List<String> answers = testerService.getTesterById(Long.parseLong(testerId)).getAnswers();

        return new ResponseEntity<>(answers, HttpStatus.OK);
    }
}
