package com.examly.toystore.controller;

import org.springframework.http.ResponseEntity;
import com.examly.toystore.model.Category;
import com.examly.toystore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "100") int size) {
        return categoryService.getAllCategories(page, size);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
    @GetMapping("/search")
    public List<Category> searchCategories(@RequestParam String keyword) {
        return categoryService.searchCategoriesByKeyword(keyword);
    }
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.saveCategory(category));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Category>> createCategories(@RequestBody List<Category> categories) {
    List<Category> savedCategories = categoryService.saveAllCategories(categories);
    return ResponseEntity.ok(savedCategories);      
    }


    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        return categoryService.updateCategory(id, updatedCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    @DeleteMapping("/all")
    public void deleteAllCategory() {
        categoryService.deleteAllCategory();
    }
}
