package main.java.model;

public class PrivateMessage extends Message {
    private User sender;
    private User receiver;
    public PrivateMessage(User sender, User receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }
}
