package org.example.warehouse.service;

import org.example.warehouse.dto.CategoryDto;
import org.example.warehouse.model.Category;
import org.example.warehouse.model.Result;
import org.example.warehouse.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getByIdCategory(Integer id) {
        return categoryRepository.findById(id);
    }

    public Result addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setActive(categoryDto.isActive());
        categoryRepository.save(category);
        return new Result(true,"Category added successfully");
    }

    public Result updateCategory(CategoryDto categoryDto, Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(categoryDto.getName());
            category.setActive(categoryDto.isActive());
            categoryRepository.save(category);
            return new Result(true, "Category updated successfully");
        }
        return new Result(true, "Category updated successfully");
    }

    public Result deleteByIdCategory(Integer id) {
        categoryRepository.deleteById(id);
        return new Result(true, "Category deleted successfully");
    }
}
