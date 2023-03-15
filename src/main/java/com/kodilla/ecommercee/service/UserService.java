package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(final User user) {
        return userRepository.save(user);
    }

    public User blockUser(final Long id) throws UserNotFoundException {
        Optional<User> blockedUser = userRepository.findById(id);

        if (blockedUser.isPresent()) {
             return userRepository.save(new User(
                        blockedUser.get().getId(),
                        blockedUser.get().getName(),
                        blockedUser.get().getSurname(),
                        false,
                        blockedUser.get().getCarts()
                    )
            );
        } else {
            throw new UserNotFoundException("User not found with given id: " + id);
        }
    }

    public String generateKeyForUser(final Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return UUID.randomUUID().toString();
        } else {
            throw new UserNotFoundException("User not found with given id: " + id);
        }
    }
}
