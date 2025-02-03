package org.example.hotelapi2.services;

import org.example.hotelapi2.model.User;
import org.example.hotelapi2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUserAndPassword(String user, String password) {
        Optional<User> usuario = userRepository.findByUsernameAndPwd(user, password);
        return usuario.orElse(null);
    }
}
