package com.crudbros.demo.model;

import com.crudbros.demo.data.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findById(long id);

    Optional<Task> findByTitle(String title);

    List<Task> findAllByTitle(String title);

    List<Task> findByUserId(long userId);

    List<Task> findByStatus(Status status);

    List<Task> findByStart(LocalDate start);

    List<Task> findByDeadLine(LocalDate deadLine);
}
