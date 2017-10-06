package model;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private final List<User> userList = new ArrayList<>();
    private int signInId = 0;

    public void addUser(User user) {
        userList.add(user);
    }

    public void deleteUser(int id) {
        userList.remove(this.findUser(id));
    }

    public boolean loginFree(String login) {
        for (User user : userList) {
            if (login.equals(user.getLogin())) {
                return false;
            }
        }
        return true;
    }

    public User findUser(int id) {
        for (User user : userList) {
            if (id == user.getId()) {
                return user;
            }
        }
        return null;
    }

    public boolean signIn (String login, String password) {
        for (User user : userList) {
            if (login.equals(user.getLogin())) {
                signInId = user.getId();
                return true;
            }
        }
        return false;
    }

    public int getNumberOfUsers() {
        return userList.size();
    }

    public int getSignInId() {
        return signInId;
    }
}