package org.example.service;

import org.example.model.User;
import repository.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void save(User user) {
        if (user.getName() != null) {
            userRepository.save(user);
        }
    }

    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    public void deleteUserById(int userId) {
        userRepository.deleteUserById(userId);
    }

    List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void deleteAllUsers() {
        userRepository.deleteAllUsers();
    }

    public boolean updateUser(User user) {
       return userRepository.updateUser(user);
    }

}

