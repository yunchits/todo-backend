package com.tasklist.backendspringboot.controller;

import com.tasklist.backendspringboot.entity.Category;
import com.tasklist.backendspringboot.entity.Priority;
import com.tasklist.backendspringboot.repository.CategoryRepository;
import com.tasklist.backendspringboot.search.CategorySearchValues;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {

        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("redundant param: is MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {

        if (category.getId() == null || category.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {

        Optional<Category> category = categoryRepository.findById(id);

        if (category.isEmpty()) {
            return new ResponseEntity("incorrect id specified\n" +
                    "id " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        } else {
            return ResponseEntity.ok(category.get());
        }

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Category> delete(@PathVariable Long id) {

        if (categoryRepository.findById(id).isEmpty()) {
            return new ResponseEntity("incorrect id specified\n" +
                    "id " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        } else {
            categoryRepository.deleteById(id);
            return new ResponseEntity("successful deletion of category with id " + id, HttpStatus.OK);
        }

    }

    @GetMapping("/all")
    public List<Category> findAll() {
        return categoryRepository.findAllByOrderByTitleAsc();
    }

    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues) {
        return ResponseEntity.ok(categoryRepository.findByTitle(categorySearchValues.getText()));
    }

}
