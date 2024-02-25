package ru.stepup.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stepup.spring.dto.Product;
import ru.stepup.spring.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("/{id}")
    public Product getByProductId(@PathVariable long id) {
        return service.findById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Product> getByUserId(@PathVariable long userId) {
        return service.findAllProductByUserId(userId);
    }
}
