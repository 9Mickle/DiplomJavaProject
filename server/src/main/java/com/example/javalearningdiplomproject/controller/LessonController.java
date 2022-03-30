package com.example.javalearningdiplomproject.controller;

import com.example.javalearningdiplomproject.dto.LessonDTO;
import com.example.javalearningdiplomproject.entity.Lesson;
import com.example.javalearningdiplomproject.facade.LessonFacade;
import com.example.javalearningdiplomproject.services.LessonService;
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
@RequestMapping("/api/lesson")
@CrossOrigin
public class LessonController {

    @Autowired
    private LessonFacade lessonFacade;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping("/{chapterId}/create")
    public ResponseEntity<Object> createLesson(@Valid @RequestBody LessonDTO lessonDTO, BindingResult result,
                                               @PathVariable("chapterId") String chapterId) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(result);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Lesson lesson = lessonService.createLesson(Long.parseLong(chapterId), lessonDTO);
        LessonDTO createdLesson = lessonFacade.lessonToLessonDTO(lesson);

        return new ResponseEntity<>(createdLesson, HttpStatus.OK);
    }

    @GetMapping("/{chapterId}/all")
    public ResponseEntity<List<LessonDTO>> getAllLessons(@PathVariable("chapterId") String chapterId){
        List<LessonDTO> lessonDTOList = lessonService.getAllLessonByChapterId(Long.parseLong(chapterId))
                .stream()
                .map(lessonFacade::lessonToLessonDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(lessonDTOList, HttpStatus.OK);
    }
}
