package com.example.javalearningdiplomproject.facade;

import com.example.javalearningdiplomproject.dto.TaskDTO;
import com.example.javalearningdiplomproject.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskFacade {

    public TaskDTO taskToTaskDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setContent(task.getContent());
        return taskDTO;
    }
}
