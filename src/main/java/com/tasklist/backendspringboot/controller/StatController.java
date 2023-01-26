package com.tasklist.backendspringboot.controller;

import com.tasklist.backendspringboot.entity.Stat;
import com.tasklist.backendspringboot.repository.StatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stat")
public class StatController {

    private final StatRepository statRepository;

    private final Long DEFAULT_ID = 1L;

    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    @GetMapping
    public ResponseEntity<Stat> getStat() {
        System.out.println("StatController: getStat");
        return ResponseEntity.ok(statRepository.findById(DEFAULT_ID).get());
    }
}
