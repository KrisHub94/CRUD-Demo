package com.crudbros.demo.API;

import com.crudbros.demo.data.Status;
import com.crudbros.demo.model.Task;
import com.crudbros.demo.model.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    //TODO: TaskNotFoundException erstellen und einfügen
    //TODO: restliche Endpoints
    //TODO:

    @GetMapping("{id}")
    Optional<Task> findById( @PathVariable long id) {
        return taskRepository.findById(id);
    }

    @GetMapping("title/{title}")
    Optional<Task> findByTitle(@PathVariable String title) {
        return taskRepository.findByTitle(title);
    }

    @GetMapping("status/{status}")
    List<Task> findByStatus(@PathVariable Status status) {
        return taskRepository.findByStatus(status);
    }

    @GetMapping("start/{start}")
    List<Task> findByStart(@PathVariable LocalDate start) {
        return taskRepository.findByStart(start);
    }

    @GetMapping("deadline/{deadline}")
    List<Task> findByDeadLine(@PathVariable LocalDate deadline) {
        return taskRepository.findByDeadLine(deadline);
    }

    @PostMapping
    Task save(@RequestBody Task newTask) {
        return taskRepository.save(newTask);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
        taskRepository.deleteById(id);
    }

    @PutMapping("{id}")
    void updateTask(@PathVariable long id, @RequestBody Task updatedTask) {
        Optional<Task> existingTask = taskRepository.findById(id); //hier .orElseThrow(TNFExcept.)

        //TODO: TNFE, dann properties von existing Task updaten .setId etc.
    }

}
