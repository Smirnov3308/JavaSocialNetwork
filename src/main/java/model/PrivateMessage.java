package model;

public class PrivateMessage extends Message {
    private int senderID;
    private int receiverID;
    private Integer rating;

    public PrivateMessage(int senderID, int receiverID, String text) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.text = text;
        this.rating = 0;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRating() {
        return rating;
    }

    public Integer getSenderID() {
        return senderID;
    }

    public void setSenderID(Integer senderID) {
        this.senderID = senderID;
    }

    public Integer getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(Integer receiverID) {
        this.receiverID = receiverID;
    }
}
