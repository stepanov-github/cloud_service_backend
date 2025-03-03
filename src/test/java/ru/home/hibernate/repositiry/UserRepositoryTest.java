package ru.home.hibernate.repositiry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.home.hibernate.entity.UserEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static ru.home.hibernate.TestData.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRepository.save(USER_1);
    }

    @Test
    void findByUsername() {
        assertEquals(Optional.of(USER_1), userRepository.findByUsername(USERNAME_1));
    }

    @Test
    void notFindByUsername() {
        assertEquals(Optional.empty(),userRepository.findByUsername(USERNAME_2));
    }

}