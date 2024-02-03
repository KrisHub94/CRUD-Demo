package com.crudbros.demo.API;

import com.crudbros.demo.data.Status;
import com.crudbros.demo.model.Task;
import com.crudbros.demo.model.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    List<Task> findAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("No tasks found");
        }
        return tasks;
    }

    @GetMapping("{id}")
    Task findById(@PathVariable long id) throws TaskNotFoundException {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
    }

    @GetMapping("title/{title}")
    Task findByTitle(@PathVariable String title) throws TaskNotFoundException {
        return taskRepository.findByTitle(title).orElseThrow(() -> new TaskNotFoundException("Task with title " + title + " not found"));
    }

    @GetMapping("userId/{userId}")
    List<Task> findByUserId(@PathVariable long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("No tasks found for user with id " + userId);
        }
        return tasks;
    }

    @GetMapping("status/{status}")
    List<Task> findByStatus(@PathVariable Status status) {
        List<Task> tasks = taskRepository.findByStatus(status);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("No tasks found with status " + status);
        }
        return tasks;
    }

    @GetMapping("start/{start}")
    List<Task> findByStart(@PathVariable LocalDate start) {
        List<Task> tasks = taskRepository.findByStart(start);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("No tasks found with start date " + start);
        }
        return tasks;
    }

    @GetMapping("deadline/{deadline}")
    List<Task> findByDeadLine(@PathVariable LocalDate deadline) {
        List<Task> tasks = taskRepository.findByDeadLine(deadline);
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("No tasks found with deadline " + deadline);
        }
        return tasks;
    }

    @PostMapping
    Task save(@RequestBody Task newTask) throws TaskAlreadyExistsException {
        if (taskRepository.existsById(newTask.getId())) {
            throw new TaskAlreadyExistsException("Task with id " + newTask.getId() + " already exists");
        }
        return taskRepository.save(newTask);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable long id) throws TaskNotFoundException {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
        taskRepository.deleteById(id);
    }

    @PutMapping("{id}")
    void updateTask(@PathVariable long id, @RequestBody Task updatedTask) throws TaskNotFoundException {
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setUserId(updatedTask.getUserId());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStart(updatedTask.getStart());
        existingTask.setDeadline(updatedTask.getDeadline());

        taskRepository.save(existingTask);
    }
}
