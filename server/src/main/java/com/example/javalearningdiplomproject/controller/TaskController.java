package com.example.javalearningdiplomproject.controller;

import com.example.javalearningdiplomproject.dto.TaskDTO;
import com.example.javalearningdiplomproject.entity.Task;
import com.example.javalearningdiplomproject.facade.TaskFacade;
import com.example.javalearningdiplomproject.services.TaskService;
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
@RequestMapping("/api/task")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskFacade taskFacade;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping("/{lessonId}/create")
    public ResponseEntity<Object> createTask(@Valid @RequestBody TaskDTO taskDTO, BindingResult bindingResult,
                                             @PathVariable("lessonId") String lessonId){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Task task = taskService.createTask(Long.parseLong(lessonId), taskDTO);
        TaskDTO createdTaskDTO = taskFacade.taskToTaskDTO(task);

        return new ResponseEntity<>(createdTaskDTO, HttpStatus.OK);
    }

    @GetMapping("/{lessonId}/all")
    public ResponseEntity<List<TaskDTO>> getAllTasks(@PathVariable("lessonId") String lessonId) {
        List<TaskDTO> taskDTOList = taskService.getAllTaskByLessonId(Long.parseLong(lessonId))
                .stream()
                .map(taskFacade::taskToTaskDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(taskDTOList, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("taskId") String taskId) {
        Task task = taskService.getTaskById(Long.parseLong(taskId));
        TaskDTO taskDTO = taskFacade.taskToTaskDTO(task);

        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }

}
