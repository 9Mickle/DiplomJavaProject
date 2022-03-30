package com.example.javalearningdiplomproject.facade;

import com.example.javalearningdiplomproject.dto.ChapterDTO;
import com.example.javalearningdiplomproject.entity.Chapter;
import org.springframework.stereotype.Component;

@Component
public class ChapterFacade {

    public ChapterDTO chapterToChapterDTO(Chapter chapter) {
        ChapterDTO chapterDTO = new ChapterDTO();
        chapterDTO.setId(chapter.getId());
        chapterDTO.setName(chapter.getName());
        chapterDTO.setNumber(chapter.getNumber());
        chapterDTO.setTitle(chapter.getTitle());
        return chapterDTO;
    }
}
