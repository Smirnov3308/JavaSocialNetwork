package model;

import dao.PrivateMessageDao;
import dao.UserDao;
import dao.impl.PrivateMessageDaoImpl;
import dao.impl.UserDaoImpl;

import java.util.List;

public class Network {
    private final UserDao userDao = new UserDaoImpl();
    private final PrivateMessageDao privateMessageDao = new PrivateMessageDaoImpl();
    private int signInId = 0;

    public boolean loginFree(String login) {
        List<User> userList = userDao.getUserList();
        for (User user : userList) {
            if (login.equals(user.getLogin())) {
                return false;
            }
        }
        return true;
    }

    public boolean signIn (String login, String password) {
        List<User> userList = userDao.getUserList();
        for (User user : userList) {
            if (login.equals(user.getLogin())) {
                signInId = user.getId();
                return true;
            }
        }
        return false;
    }

    public int getSignInId() {
        return signInId;
    }

    public void logOut() { signInId = 0; }

    public UserDao getUserDao() {
        return userDao;
    }

    public PrivateMessageDao getPrivateMessageDao() {
        return privateMessageDao;
    }
}
