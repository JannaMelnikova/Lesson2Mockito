package org.example.service;
import org.example.model.User;
import org.example.dataDase.User_db;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest2 {
    @BeforeEach
    public void setUp() {
        // Очистка и добавление тестовых данных перед каждым тестом
        User_db.users.clear();
        User_db.users.add(new User(1, 50000.0, "John"));
        User_db.users.add(new User(2, 60000.0, "Jane"));
        User_db.users.add(new User(3, 70000.0, "Alice"));
    }

    @Mock
    UserRepositoryImpl userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void testAddUsersToList() {
        User user1 = new User(1, 50000.0, "John");
        User user2 = new User(2, 60000.0, "Jane");
        User user3 = new User(3, 70000.0, "Alice");

        User_db.users.add(user1);
        User_db.users.add(user2);
        User_db.users.add(user3);

        // Проверка размера списка
        assertEquals(3, User_db.users.size());

        // Проверка, что пользователи корректно добавлены
        assertTrue(User_db.users.contains(user1));
        assertTrue(User_db.users.contains(user2));
        assertTrue(User_db.users.contains(user3));
    }

    @Test
    public void getUserById() {
        User user = User_db.users.get(0);  // Получаем первого пользователя
        Assertions.assertEquals(1, user.getId());

        // Проверка пользователя, которого нет в бд
        User nonExistentUser = User_db.getUserById(4);
        Assertions.assertNull(nonExistentUser);
    }

    @Test
    void deleteUserById() {
        // Создаем пользователя с ID 4
        int userId = 4;

        // Вызываем метод deleteUserById
        userService.deleteUserById(4);

        // Проверка, что пользователь был удален
        User user = User_db.users.stream().filter(u -> u.getId() == 4).findFirst().orElse(null);
        Assertions.assertNull(user);
    }

    @Test
    void deleteAllUsers() { //не пройден есть ошибка True и False
        userService.deleteAllUsers();
        // Проверка, что пользователи удалены
        Assertions.assertTrue(User_db.users.isEmpty());
    }

    @Test
    void updateUser() {
        User user3 = new User(3, 70000.0, "Alice");

        //при вызове метода updateUser у userRepository (он является заглушкой) должен вернуть true
        when(userRepository.updateUser(user3)).thenReturn(true);

        // получаем результат обновления, который должен быть true так как userRepository возвращает true
        boolean result = userService.updateUser(user3);

        User updatedUser = User_db.users.stream()
                .filter(user -> user.getId() == 3)
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(updatedUser);

    }

}
