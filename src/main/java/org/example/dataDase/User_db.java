package org.example.dataDase;

import org.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class User_db {
    public static List<User> users = new ArrayList<>();

    public static User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;//если пользователя еще не существует в бд
    }

    public static void deleteAllUsers() {
        users.clear();
    }
}
