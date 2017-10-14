package main.java.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;

    private final List<User> friendList = new ArrayList<>();
    private final List<Group> groupList = new ArrayList<>();
    private final List<Post> postList = new ArrayList<>();


    public User(int id, String login, String password, String firstName, String lastName) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getNumberOfFriends() {
        return friendList.size();
    }

    public void showFriendList() {
        for (User friend : friendList) {
            System.out.println(friend.getId() + ") " + friend.getFirstName() + " " + friend.getLastName());
        }
        if (friendList.size() == 0) System.out.println("You have no friends");
    }

    public boolean addFriend(User user) {
        if (!friendList.contains(user)) {
            friendList.add(user);
            return true;
        }
        return false;
    }

    public boolean removeFriend(User user) {
        if (friendList.contains(user)) {
            friendList.remove(user);
            return true;
        }
        return false;
    }

    public void showPostsList() {
        for (Post post : postList) {
            System.out.println(" - " + post.getMessage());
        }
        if (postList.size() == 0) System.out.println("Posts not found");
        System.out.println("---------------------");
    }

    public void showUserName() {
        System.out.println("=====================");
        System.out.println(" " + this.getFirstName() + " " + this.getLastName());
        System.out.println("=====================");
    }

    public void addPost(Post post) {
        postList.add(post);
    }
}
