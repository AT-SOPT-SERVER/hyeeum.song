package org.sopt.service;

import org.sopt.domain.User;
import org.sopt.exception.UserNameBlankException;
import org.sopt.exception.UserNameLengthException;
import org.sopt.repository.UserRepository;
import org.sopt.validator.UserNameValidator;
import org.springframework.stereotype.Service;

import static org.sopt.constant.LimitConstant.USER_NAME_LENGTH_LIMIT;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(final String name) {
        validateUserName(name);

        User user = new User(name);

        userRepository.save(user);
    }

    public void validateUserName(final String name) {
        if (UserNameValidator.isUserNameBlank(name)) throw new UserNameBlankException();
        if (UserNameValidator.isUserNameExceedsLength(name, USER_NAME_LENGTH_LIMIT))
            throw new UserNameLengthException(USER_NAME_LENGTH_LIMIT);
    }
}
