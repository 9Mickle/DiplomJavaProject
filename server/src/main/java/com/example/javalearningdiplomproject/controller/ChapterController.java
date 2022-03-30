package com.example.javalearningdiplomproject.controller;

import com.example.javalearningdiplomproject.dto.ChapterDTO;
import com.example.javalearningdiplomproject.entity.Chapter;
import com.example.javalearningdiplomproject.facade.ChapterFacade;
import com.example.javalearningdiplomproject.services.ChapterService;
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
@RequestMapping("api/chapter")
@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterFacade chapterFacade;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping("/create")
    public ResponseEntity<Object> createChapter(@Valid @RequestBody ChapterDTO chapterDTO,
                                                BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Chapter chapter = chapterService.createChapter(chapterDTO);
        ChapterDTO createdChapter = chapterFacade.chapterToChapterDTO(chapter);

        return new ResponseEntity<>(createdChapter, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ChapterDTO>> getAllChapters() {
        List<ChapterDTO> chapterDTOList = chapterService.getAllChapters()
                .stream()
                .map(chapterFacade::chapterToChapterDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(chapterDTOList, HttpStatus.OK);
    }

}
