package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private final List<User> userList = new ArrayList<>();
    private int signInId = 1;

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

    public void showUserList() {
        for (User user : userList) {
            if(user.getId() != this.signInId)
                System.out.println(user.getId() + ") " + user.getFirstName() + " " + user.getLastName());
        }
        if (userList.isEmpty()) System.out.println("Users not found");
    }

    public int getNumberOfUsers() {
        return userList.size();
    }

    public int getSignInId() {
        return signInId;
    }
    public void logOut() { signInId = 0; }
}