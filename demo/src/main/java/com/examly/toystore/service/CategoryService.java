package com.examly.toystore.service;

import com.examly.toystore.model.Category;
import com.examly.toystore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(int page, int size) {
        return categoryRepository.findAll(PageRequest.of(page, size)).getContent();
    }
    

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
    public List<Category> searchCategoriesByKeyword(String keyword) {
        return categoryRepository.searchByKeyword(keyword);
    }
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
    public List<Category> saveAllCategories(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(updatedCategory.getName());
            return categoryRepository.save(category);
        }).orElse(null);
    }
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    public void deleteAllCategory() {
        categoryRepository.deleteAll();
    }
}
