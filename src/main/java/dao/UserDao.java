package dao;

import model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void editUser(User user);
    User findUser(int id);
    List<User> getUserList();
}
