package ru.stepup.spring.service;

import ru.stepup.spring.dto.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProductByUserId(long userId);

    Product findById(long productId);
}
