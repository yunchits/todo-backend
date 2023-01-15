package com.tasklist.backendspringboot.controller;

import com.tasklist.backendspringboot.entity.Priority;
import com.tasklist.backendspringboot.repository.PriorityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private PriorityRepository priorityRepository;

    public PriorityController(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @PostMapping ("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority) {

        //ResponseEntity special object that can contain object + status

        if (priority.getId() != null && priority.getId() != 0) {
            return new ResponseEntity("redundant param: is MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(priorityRepository.save(priority)); // .ok() for acceptable object
    }

}
