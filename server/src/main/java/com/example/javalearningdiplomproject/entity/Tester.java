package com.example.javalearningdiplomproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность для проведения тестирования пользователя по пройденному материалу.
 */
@Entity
@Data
@NoArgsConstructor
public class Tester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "text")
    private String question;
    @Column(name = "right_answer")
    private String rightAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;

    @ElementCollection
    @CollectionTable(name = "answers",
        joinColumns = @JoinColumn(name = "tester_id"))
    private List<String> answers = new ArrayList<>();
}
