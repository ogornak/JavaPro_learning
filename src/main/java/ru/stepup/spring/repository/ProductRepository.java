package ru.stepup.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepup.spring.entity.ProductEntity;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByAccount(String account);
}
