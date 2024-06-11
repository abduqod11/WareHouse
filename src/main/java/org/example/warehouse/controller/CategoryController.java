package org.example.warehouse.controller;

import org.example.warehouse.dto.CategoryDto;
import org.example.warehouse.model.Category;
import org.example.warehouse.model.Result;
import org.example.warehouse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Category")

public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public List<Category> getCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public Optional<Category> getCategoryById(@PathVariable Integer id) {
        return categoryService.getByIdCategory(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer id) {
        return categoryService.updateCategory(categoryDto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    public Result deleteCategoryById(@PathVariable Integer id) {
        return categoryService.deleteByIdCategory(id);
    }

}
