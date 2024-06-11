package org.example.warehouse.service;

import org.example.warehouse.dto.ProductDto;
import org.example.warehouse.model.Category;
import org.example.warehouse.model.Product;
import org.example.warehouse.model.Result;
import org.example.warehouse.repository.CategoryRepository;
import org.example.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service

public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
            return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.get();
    }

    public Result createProduct(ProductDto productdto) {
        Product product = new Product();
        product.setName(productdto.getName());
        product.setCode(productdto.getCode());
        product.setActive(productdto.isActive());
        Optional<Category> optionalCategory = categoryRepository.findById(productdto.getCategoryId());
        Category category = optionalCategory.get();
        product.setCategory(category);
        productRepository.save(product);
        return new Result(true,"Product added successfully");
    }

    public Result updateProduct(Integer id, ProductDto productdto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productdto.getName());
            product.setCode(productdto.getCode());
            product.setActive(productdto.isActive());
            Optional<Category> categoryOptional = categoryRepository.findById(productdto.getCategoryId());
            Category category = categoryOptional.get();
            product.setCategory(category);
            productRepository.save(product);
            return new Result(true,"Product updated successfully");
        }
        return new Result(false,"Product not found");
    }

    public Result deleteProductById(Integer id) {
        productRepository.deleteById(id);
        return new Result(true,"Product deleted successfully");
    }
}