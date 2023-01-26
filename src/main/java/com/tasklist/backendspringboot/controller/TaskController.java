package com.tasklist.backendspringboot.controller;

import com.tasklist.backendspringboot.entity.Priority;
import com.tasklist.backendspringboot.entity.Task;
import com.tasklist.backendspringboot.repository.TaskRepository;
import com.tasklist.backendspringboot.search.TaskSearchValues;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        System.out.println("TaskController: findAll");
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task) {

        System.out.println("TaskController: findAll");

        if (task.getId() != null && task.getId() != 0) {
            return new ResponseEntity("redundant param: is MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskRepository.save(task));
    }

    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody Task task) {

        System.out.println("TaskController: update");

        if (task.getId() == null || task.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(taskRepository.save(task));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {

        System.out.println("TaskController: findById");

        Optional<Task> task = taskRepository.findById(id);

        if (task.isEmpty()) {
            return new ResponseEntity("incorrect id specified\n" +
                    "id " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        } else {
            return ResponseEntity.ok(task.get());
        }

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {

        System.out.println("TaskController: delete");

        if (taskRepository.findById(id).isEmpty()) {
            return new ResponseEntity("incorrect id specified\n" +
                    "id " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        } else {
            taskRepository.deleteById(id);
            return new ResponseEntity("successful deletion of category with id " + id, HttpStatus.OK);
        }

    }

    @PostMapping("/search")
    public ResponseEntity<List<Task>> search(@RequestBody TaskSearchValues taskSearchValues) {

        System.out.println("TaskController: search");

        String title = taskSearchValues.getTitle() != null ? taskSearchValues.getTitle() : null;
        Integer completed = taskSearchValues.getCompleted () != null ? taskSearchValues.getCompleted () : null;
        Long priorityId = taskSearchValues.getPriorityId () != null ? taskSearchValues.getPriorityId () : null;
        Long categoryId = taskSearchValues.getCategoryId () != null ? taskSearchValues.getCategoryId() : null;

        return ResponseEntity.ok(taskRepository.findByParams(title, completed, priorityId, categoryId));
    }

}
