package ru.stepup.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepup.spring.dto.Product;
import ru.stepup.spring.repository.ProductRepository;
import ru.stepup.spring.service.ProductService;
import ru.stepup.spring.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final UserService userService;
    private final ProductRepository repository;

    @Override
    public List<Product> findAllProductByUserId(long userId) {
        var user = userService.findById(userId);
        return repository.findByAccount(user.getAccount())
                .stream()
                .map(entity -> new Product()
                        .setId(entity.getId())
                        .setAccount(entity.getAccount())
                        .setBalance(entity.getBalance())
                        .setType(entity.getType()))
                .collect(Collectors.toList());
    }

    @Override
    public Product findById(long productId) {
        var entity = repository.findById(productId);
        if (entity.isEmpty()) {
            throw new NoSuchElementException("Product " + productId + " was not found");
        }
        return new Product()
                .setId(productId)
                .setAccount(entity.get().getAccount())
                .setBalance(entity.get().getBalance())
                .setType(entity.get().getType());
    }
}
