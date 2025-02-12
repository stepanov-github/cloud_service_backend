package ru.home.hibernate.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.home.hibernate.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}
