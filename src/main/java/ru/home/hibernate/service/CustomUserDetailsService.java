package ru.home.hibernate.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.home.hibernate.entity.UserEntity;
import ru.home.hibernate.exception.UnauthorizedErrorException;
import ru.home.hibernate.repositiry.UserRepository;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UnauthorizedErrorException("User not found"));

//        UserDetails userDetails = User.builder()
//                .username(userEntity.getUsername())
//                .password(userEntity.getPassword())
//                .roles(userEntity.getRole())
//                .build();

        return userEntity;
    }
}
