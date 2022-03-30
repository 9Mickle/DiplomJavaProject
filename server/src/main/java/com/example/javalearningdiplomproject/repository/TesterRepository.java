package com.example.javalearningdiplomproject.repository;

import com.example.javalearningdiplomproject.entity.Tester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TesterRepository extends JpaRepository<Tester, Long> {

    List<Tester> findByTaskId(Long taskId);

    Optional<Tester> findById(Long taskId);
}
