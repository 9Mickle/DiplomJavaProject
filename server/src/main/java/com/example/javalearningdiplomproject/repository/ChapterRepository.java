package com.example.javalearningdiplomproject.repository;

import com.example.javalearningdiplomproject.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    List<Chapter> findAllByOrderByNumberAsc();

    Optional<Chapter> findById(Long id);
}
