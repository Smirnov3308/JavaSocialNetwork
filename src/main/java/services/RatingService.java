package main.java.services;

import main.java.model.PrivateMessage;
import main.java.model.User;
import main.java.model.WordRating;

import java.util.List;

public class RatingService {
    public void updateRating(User user, PrivateMessage message) {
        int messageRating = 0;
        List<WordRating> wordRatingList= user.getWordRatingList();
        String text = message.getText();
        String[] wordList = text.toLowerCase().replaceAll("[^a-z\\s]", "").trim().split("\\s");

        for (String word : wordList) {
            for (WordRating wordRating : wordRatingList) {
                if (wordRating.getWord().equals(word)) {
                    messageRating = messageRating + wordRating.getRating();
                    break;
                }
            }
        }
        message.setRating(messageRating);
    }
}
