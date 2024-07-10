package repository;

import org.example.dataDase.User_db;
import org.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public void save(User user) {
        User_db.users.add(user);
    }

    @Override
    public User findUserById(int id) {
        for (User user : User_db.users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean deleteUserById(int id) {
        return User_db.users.removeIf(user -> user.getId() == id);//removeIf удаление
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(User_db.users);//возвращаем новый лист с объектами старого листа
    }

    @Override
    public void deleteAllUsers() {
        User_db.users.clear();
    }

    @Override
    public boolean updateUser(User user) {
        for (User user1 : User_db.users) {
            if (user1.getId() == user.getId()) {
                user1.setName(user.getName());
                user1.setSalary(user.getSalary());
                return  true;
            }
        }
        return false;
    }
}
