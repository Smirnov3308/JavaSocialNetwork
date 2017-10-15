package main.java.model;

import java.util.*;

public class User {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;

    private final List<User> friendList = new ArrayList<>();
    private final List<Post> postList = new ArrayList<>();
    private final List<WordRating> wordRatingList = new ArrayList<>();


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
            System.out.println(" - " + post.getText());
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


    public void addMessage(String text) {
        String[] wordList = text.toLowerCase().replaceAll("[^a-z\\s]", "").trim().split("\\s");
        boolean contains;
        for (String word : wordList) {
            contains = false;
            for (WordRating wordRating : wordRatingList) {
                if (wordRating.getWord().equals(word)) {
                    wordRating.incRating();
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                wordRatingList.add(new WordRating(word));
            }
        }
        // Сортируем получившийся массив
        Collections.sort(wordRatingList, Collections.reverseOrder(WordRating.COMPARE_BY_RATING));
    }

    public void showTop10Word() {
        System.out.println("Word rating:");
        for (int i = 0; (i < 10) && i < wordRatingList.size() ; i++) {
            System.out.println((i + 1) + " - " + wordRatingList.get(i).getWord());
        }
    }

    public List<WordRating> getWordRatingList() {
        return wordRatingList;
    }
}
