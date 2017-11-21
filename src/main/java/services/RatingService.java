package services;

import model.PrivateMessage;
import model.User;
import model.WordRating;

import java.util.List;
import java.util.Locale;

public class RatingService {
    public void updateRating(User user, PrivateMessage message) {
        int messageRating = 0;
        List<WordRating> wordRatingList= user.getWordRatingList();
        String text = message.getText();
        String[] wordList = text.toLowerCase(Locale.ENGLISH).replaceAll("[^a-z\\s]", "").trim().split("\\s");

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
