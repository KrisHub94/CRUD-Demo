package com.crudbros.demo.API;

import com.crudbros.demo.data.Status;
import com.crudbros.demo.model.Task;
import com.crudbros.demo.model.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> findAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        if (tasks.isEmpty()) {
            return new ResponseEntity<>("No tasks saved", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<Object> findTaskById(@PathVariable Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isEmpty()) {
            return new ResponseEntity<>("TaskId not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taskOptional.get(), HttpStatus.OK);
    }

    @GetMapping("title/{title}")
    public ResponseEntity<Object> findByTitle(@PathVariable String title) {
        Optional<Task> task = taskRepository.findByTitle(title);
        if (task.isEmpty()) {
            return new ResponseEntity<>("Title not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
    }

    @GetMapping("sameTitles/{title}")
    public ResponseEntity<Object> findAllByTitle(@PathVariable String title) {
        List<Task> tasks = taskRepository.findAllByTitle(title);
        if (tasks.isEmpty()) {
            return new ResponseEntity<>("No tasks found with this title", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }
    }


    @GetMapping("userId/{userId}")
    ResponseEntity<Object> findByUserId(@PathVariable long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        if (tasks.isEmpty()) {
            return new ResponseEntity<>("UserId not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("status/{status}")
    ResponseEntity<Object> findByStatus(@PathVariable Status status) {
        List<Task> tasks = taskRepository.findByStatus(status);
        if (tasks.isEmpty()) {
            return new ResponseEntity<>("Status not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("start/{start}")
    ResponseEntity<Object> findByStart(@PathVariable LocalDate start) {
        List<Task> tasks = taskRepository.findByStart(start);
        if (tasks.isEmpty()) {
            return new ResponseEntity<>("Start date not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("deadline/{deadline}")
    ResponseEntity<Object> findByDeadLine(@PathVariable LocalDate deadline) {
        List<Task> tasks = taskRepository.findByDeadLine(deadline);
        if (tasks.isEmpty()) {
            return new ResponseEntity<>("Deadline not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Object> save(@RequestBody Task newTask) {
        List<Task> tasksWithNewTitle = taskRepository.findAllByTitle(newTask.getTitle());

        boolean isPresent = !tasksWithNewTitle.isEmpty();

        if (isPresent) {
            return new ResponseEntity<>("Task with the same title already exists", HttpStatus.CONFLICT);
        }

        Task savedTask = taskRepository.save(newTask);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }


    @DeleteMapping("{id}")
    ResponseEntity<Object> delete(@PathVariable long id) {
        if (!taskRepository.existsById(id)) {
            return new ResponseEntity<>("Task to delete not found", HttpStatus.NOT_FOUND);
        }
        taskRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    ResponseEntity<?> updateTask(@PathVariable long id, @RequestBody Task updatedTask) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    List<Task> tasksWithUpdatedTaskTitle = taskRepository.findAllByTitle(updatedTask.getTitle());

                    boolean isConflict = tasksWithUpdatedTaskTitle.stream()
                            .anyMatch(task -> task.getId() != (existingTask.getId()));

                    if (isConflict) {
                        return new ResponseEntity<>("Task with the same title already exists", HttpStatus.CONFLICT);
                    }

                    existingTask.setTitle(updatedTask.getTitle());
                    existingTask.setUserId(updatedTask.getUserId());
                    existingTask.setStatus(updatedTask.getStatus());
                    existingTask.setDescription(updatedTask.getDescription());
                    existingTask.setStart(updatedTask.getStart());
                    existingTask.setDeadline(updatedTask.getDeadline());

                    Task savedTask = taskRepository.save(existingTask);
                    return new ResponseEntity<>(savedTask, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND));
    }

}
