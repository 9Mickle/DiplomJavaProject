package com.example.javalearningdiplomproject.repository;

import com.example.javalearningdiplomproject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByLessonId(Long lessonId);

    Optional<Task> findById(Long taskId);
}
