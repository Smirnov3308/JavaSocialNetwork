package model;

public class PrivateMessage extends Message {
    private User sender;
    private User receiver;
    private Integer rating;

    public PrivateMessage(User sender, User receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = message;
        this.rating = 0;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRating() {
        return rating;
    }
}
