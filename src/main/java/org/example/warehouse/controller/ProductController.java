package org.example.warehouse.controller;

import org.example.warehouse.dto.ProductDto;
import org.example.warehouse.model.Product;
import org.example.warehouse.model.Result;
import org.example.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result addProduct(@RequestBody ProductDto productdto) {
        return productService.createProduct(productdto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Result updateProduct(@RequestBody ProductDto productdto,@PathVariable Integer id) {
        return productService.updateProduct(id, productdto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','USER')")
    public Result deleteProduct(@PathVariable Integer id) {
        return productService.deleteProductById(id);
    }

}
