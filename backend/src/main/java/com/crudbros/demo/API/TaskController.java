package com.crudbros.demo.API;

import com.crudbros.demo.model.Task;
import com.crudbros.demo.model.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    List<Task> findAllTasks() {return taskRepository.findAll();}

    //TODO: TaskNotFoundException erstellen
    //TODO: restliche Endpoints

    @GetMapping("id/{id}")
    Optional<Task> findById( @PathVariable long id) {
        return taskRepository.findById(id);
    }


}
