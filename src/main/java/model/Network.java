package main.java.model;

import main.java.services.RatingService;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private final List<User> userList = new ArrayList<>();
    private final List<PrivateMessage> messageList = new ArrayList<>();
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

    public void showMessageList(User sender, User receiver) {
        System.out.println("---------------------");
        for (PrivateMessage message : messageList) {
            if ((message.getSender() == sender) && (message.getReceiver() == receiver))
                System.out.println("from: " + sender.getFirstName() + " to " + receiver.getFirstName() + ": "
                        + message.getText());
        }
        System.out.println("---------------------");
    }

    public void showMessageList(User sender) {
        for (PrivateMessage message : messageList) {
            if ((message.getSender() == sender))
                System.out.println("from " + sender.getFirstName() + " to " + message.getReceiver().getFirstName() +
                        " (rating = " + message.getRating() + ") - " + message.getText());
        }
        System.out.println("---------------------");
    }

    private void updateMessagesRating(User user) {
        RatingService ratingService = new RatingService();
        for(PrivateMessage message : messageList) {
            if (message.getSender() == user) {
                ratingService.updateRating(user, message);
            }
        }
    }

    public void addPM(User sender, User receiver, String message) {
        PrivateMessage privateMessage = new PrivateMessage(sender, receiver, message);
        messageList.add(privateMessage);
        sender.addMessage(privateMessage.getText());
        updateMessagesRating(sender);
    }

}