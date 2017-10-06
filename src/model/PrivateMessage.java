package model;

public class PrivateMessage extends Message {
    private User sender;
    private User addressee;
    public PrivateMessage(User sender, User addressee, String message) {
        this.sender = sender;
        this.addressee = addressee;
        this.message = message;
    }
}
