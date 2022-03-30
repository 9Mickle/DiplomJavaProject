package com.example.javalearningdiplomproject.services;

import com.example.javalearningdiplomproject.dto.LessonDTO;
import com.example.javalearningdiplomproject.entity.Chapter;
import com.example.javalearningdiplomproject.entity.Lesson;
import com.example.javalearningdiplomproject.exception.ChapterNotFoundException;
import com.example.javalearningdiplomproject.repository.ChapterRepository;
import com.example.javalearningdiplomproject.repository.LessonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class LessonService {

    public static final Logger LOG = LoggerFactory.getLogger(LessonService.class);

    private final LessonRepository lessonRepository;
    private final ChapterRepository chapterRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository, ChapterRepository chapterRepository) {
        this.lessonRepository = lessonRepository;
        this.chapterRepository = chapterRepository;
    }

    public Lesson createLesson(Long chapterId, LessonDTO lessonDTO) {
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new ChapterNotFoundException("Chapter not found by id: " + chapterId));

        Lesson lesson = new Lesson();
        lesson.setChapter(chapter);
        lesson.setName(lessonDTO.getName());
        lesson.setTitle(lessonDTO.getTitle());

        LOG.info("Save lesson for Chapter: {}", chapter.getId());
        return lessonRepository.save(lesson);
    }

    //todo здесь можно отфлитровать уроки по номеру (если его добавить в сущность).
    public List<Lesson> getAllLessonByChapterId(Long chapterId) {
        return lessonRepository.findAllByChapterId(chapterId);
    }


}
