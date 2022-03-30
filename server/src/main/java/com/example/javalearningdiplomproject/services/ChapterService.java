package com.example.javalearningdiplomproject.services;

import com.example.javalearningdiplomproject.dto.ChapterDTO;
import com.example.javalearningdiplomproject.entity.Chapter;
import com.example.javalearningdiplomproject.exception.ChapterNotFoundException;
import com.example.javalearningdiplomproject.repository.ChapterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ChapterService {

    public static final Logger LOG = LoggerFactory.getLogger(ChapterService.class);

    private final ChapterRepository chapterRepository;

    @Autowired
    public ChapterService(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }

    public Chapter createChapter(ChapterDTO chapterDTO) {
        Chapter chapter = new Chapter();
        chapter.setName(chapterDTO.getName());
        chapter.setTitle(chapterDTO.getTitle());
        chapter.setNumber(chapterDTO.getNumber());

        LOG.info("Saving Chapter");
        return chapterRepository.save(chapter);
    }

    /**
     * Поиск всех разделов с сортировкой по номеру.
     * @return лист разделов.
     */
    public List<Chapter> getAllChapters() {
        return chapterRepository.findAllByOrderByNumberAsc();
    }

    public Chapter getChapterById(Long chapterId) {
        return chapterRepository.findById(chapterId)
                .orElseThrow(() -> new ChapterNotFoundException("Chapter not found by id: " + chapterId));
    }
}
