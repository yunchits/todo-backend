package com.tasklist.backendspringboot.controller;

import com.tasklist.backendspringboot.entity.Priority;
import com.tasklist.backendspringboot.repository.PriorityRepository;
import com.tasklist.backendspringboot.search.PrioritySearchValues;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @PostMapping ("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority) {

        System.out.println("PriorityController: add");

        //ResponseEntity special object that can contain object + status

        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("redundant param: is MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priority)); // .ok() for acceptable object
    }

    @PutMapping("/update")
    public ResponseEntity<Priority> update(@RequestBody Priority priority) {

        System.out.println("PriorityController: update");

        if (priority.getId() == null || priority.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            return new ResponseEntity("missed param: color", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priority));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findById(@PathVariable Long id) {

        System.out.println("PriorityController: findById");

        Optional<Priority> priority = priorityRepository.findById(id);

        if (priority.isEmpty()) {
            return new ResponseEntity("incorrect id specified\n" +
                    "id " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        } else {
            return ResponseEntity.ok(priority.get());
        }

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Priority> delete(@PathVariable Long id) {

        System.out.println("PriorityController: delete");

        if (priorityRepository.findById(id).isEmpty()) {
            return new ResponseEntity("incorrect id specified\n" +
                    "id " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        } else {
            priorityRepository.deleteById(id);
            return new ResponseEntity("successful deletion of category with id " + id, HttpStatus.OK);
        }

    }

    @GetMapping("/all")
    public List<Priority> findAll() {
        System.out.println("PriorityController: findAll");
        return priorityRepository.findAllByOrderByTitleAsc();
    }

    @PostMapping("/search")
    public ResponseEntity<List<Priority>> search(@RequestBody PrioritySearchValues prioritySearchValues) {
        System.out.println("PriorityController: search");
        return ResponseEntity.ok(priorityRepository.findByTitle(prioritySearchValues.getText()));
    }
}
