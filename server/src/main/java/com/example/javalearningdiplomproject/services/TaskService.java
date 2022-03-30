package com.example.javalearningdiplomproject.services;

import com.example.javalearningdiplomproject.dto.TaskDTO;
import com.example.javalearningdiplomproject.entity.Lesson;
import com.example.javalearningdiplomproject.entity.Task;
import com.example.javalearningdiplomproject.exception.LessonNotFoundException;
import com.example.javalearningdiplomproject.repository.LessonRepository;
import com.example.javalearningdiplomproject.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    public static final Logger LOG = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, LessonRepository lessonRepository) {
        this.taskRepository = taskRepository;
        this.lessonRepository = lessonRepository;
    }


    public Task createTask(Long lessonId, TaskDTO taskDTO) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new LessonNotFoundException("Lesson not found by id: " + lessonId));

        Task task = new Task();
        task.setLesson(lesson);
        task.setTitle(taskDTO.getTitle());
        task.setContent(taskDTO.getContent());

        LOG.info("Saving Task for Lesson: {}", task.getId());
        return taskRepository.save(task);
    }

    public List<Task> getAllTaskByLessonId(Long lessonId) {
        return taskRepository.findAllByLessonId(lessonId);
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.getById(taskId);
    }
}
