package dao;

import model.PrivateMessage;
import model.User;

import java.util.List;

public interface PrivateMessageDao {
    void addPrivateMessage(User sender, User receiver, String text);
    List<PrivateMessage> getMessages(User sender, User receiver);
    List<PrivateMessage> getMessages(User sender);

}