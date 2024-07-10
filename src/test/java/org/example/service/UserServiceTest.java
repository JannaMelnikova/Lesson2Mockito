package org.example.service;

import org.example.dataDase.User_db;
import org.example.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.UserRepository;
import repository.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest { //тестирование сервиса

    @Mock
    UserRepositoryImpl userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void save() {
        User user = new User(7, 100.000, "Alisa");

        userService.save(user);
        verify(userRepository).save(user);
    }

    @Test
    void findUserById() {
        when(userRepository.findUserById(7)).thenReturn(new User(7,1211.0,"Valera"));
        User userById = userService.findUserById(7);
        verify(userRepository).findUserById(7);

        assertEquals(new User(7,1211.0,"Valera"),userById);
    }

    @Test
    void deleteAllUsers() {
        userService.deleteAllUsers();
        verify(userRepository).deleteAllUsers();
    }

    @Test
    void deleteUserById() {
        userService.deleteUserById(5);
        verify(userRepository).deleteUserById(5);

    }

    @Test
    void updateUser() {
        User user7 = new User(7, 100.000, "Alena");

        //при вызове метода updateUser у userRepository (он является заглушкой) должен вернуть true
        when(userRepository.updateUser(user7)).thenReturn(true);

        // получаем результат обновления, который должен быть true так как userRepository возвращает true
        boolean result = userService.updateUser(user7);

        // убедиться, что метод был запущен с этим параметром
        verify(userRepository).updateUser(user7);

        //тест пройден
        assertTrue(result);


    }

}

