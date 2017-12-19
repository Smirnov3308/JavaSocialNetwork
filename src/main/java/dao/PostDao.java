package dao;

import model.Post;
import model.User;

import java.util.List;

public interface PostDao {
    void addPost(User user, String text);
    List<Post> getMessages(User user);
}