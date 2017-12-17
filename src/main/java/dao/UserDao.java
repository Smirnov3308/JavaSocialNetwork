package dao;

import model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void editUser(User user);
    int getCount();
    User findUser(int id);
    List<User> getUserList();
}
