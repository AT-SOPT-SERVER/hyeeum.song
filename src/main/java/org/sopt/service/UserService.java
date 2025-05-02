package org.sopt.service;

import org.sopt.domain.User;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(final String name) {
        User user = new User(name);

        userRepository.save(user);
    }
}
