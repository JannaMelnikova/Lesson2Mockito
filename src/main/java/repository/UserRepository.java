package repository;

import org.example.model.User;

import java.util.List;

public interface UserRepository {
    void save(User user);

    User findUserById(int id);

    boolean deleteUserById(int id);

    List<User> getAllUsers();

     void deleteAllUsers();

     boolean updateUser(User user);

}
