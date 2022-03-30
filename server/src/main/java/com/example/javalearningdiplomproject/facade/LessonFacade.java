package com.example.javalearningdiplomproject.facade;

import com.example.javalearningdiplomproject.dto.LessonDTO;
import com.example.javalearningdiplomproject.entity.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonFacade {

    public LessonDTO lessonToLessonDTO(Lesson lesson) {
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setId(lesson.getId());
        lessonDTO.setName(lesson.getName());
        lessonDTO.setTitle(lesson.getTitle());
        return lessonDTO;
    }
}
