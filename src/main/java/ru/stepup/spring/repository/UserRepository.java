package ru.stepup.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepup.spring.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
