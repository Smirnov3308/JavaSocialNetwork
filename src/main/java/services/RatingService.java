package services;

import dao.PrivateMessageDao;
import model.PrivateMessage;
import model.User;
import model.WordRating;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class RatingService {
    public static int getRating(User user, PrivateMessage message, PrivateMessageDao privateMessageDao) {
        int messageRating = 0;
        List<WordRating> wordRatingList = new ArrayList<>();
        List<PrivateMessage> messageList = privateMessageDao.getMessages(user);

        for (PrivateMessage privateMessage : messageList) {
            addMessage(privateMessage.getText(), wordRatingList);
        }

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
        return messageRating;
    }

    private static void addMessage(String text, List<WordRating> wordRatingList) {
        String[] wordList = text.toLowerCase(Locale.ENGLISH).replaceAll("[^a-z\\s]", "").trim().split("\\s");
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

    public static void showTop10(User user, PrivateMessageDao privateMessageDao) {
        List<WordRating> wordRatingList = new ArrayList<>();
        List<PrivateMessage> messageList = privateMessageDao.getMessages(user);

        for (PrivateMessage privateMessage : messageList) {
            addMessage(privateMessage.getText(), wordRatingList);
        }

        System.out.println("Word rating:");
        for (int i = 0; (i < 10) && i < wordRatingList.size() ; i++) {
            System.out.println((i + 1) + " - " + wordRatingList.get(i).getWord());
        }
    }
}
