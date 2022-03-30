package com.example.javalearningdiplomproject.services;

import com.example.javalearningdiplomproject.dto.TesterDTO;
import com.example.javalearningdiplomproject.entity.Task;
import com.example.javalearningdiplomproject.entity.Tester;
import com.example.javalearningdiplomproject.exception.TaskNotFoundException;
import com.example.javalearningdiplomproject.repository.TaskRepository;
import com.example.javalearningdiplomproject.repository.TesterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TesterService {

    public static final Logger LOG = LoggerFactory.getLogger(TesterService.class);

    private final TesterRepository testerRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TesterService(TesterRepository testerRepository, TaskRepository taskRepository) {
        this.testerRepository = testerRepository;
        this.taskRepository = taskRepository;
    }

    public List<Tester> getAllTesterByTaskId(Long taskId) {
        return testerRepository.findByTaskId(taskId);
    }

    public Tester createTester(Long taskId, TesterDTO testerDTO) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found by id: " + taskId));

        Tester tester = new Tester();
        tester.setTask(task);
        tester.setQuestion(testerDTO.getQuestion());
        tester.setRightAnswer(testerDTO.getRightAnswer());
        tester.setAnswers(testerDTO.getAnswers());

        LOG.info("Saving Tester for Task: {}", tester.getId());
        return testerRepository.save(tester);
    }

    public Tester getTesterById(Long testerId) {
        return testerRepository.getById(testerId);
    }

    public void addAnswers(Long testerId, List<String> answers) {
        testerRepository.getById(testerId).setAnswers(answers);
    }
}
