package ru.bankingservices.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bankingservices.model.User;
import ru.bankingservices.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
