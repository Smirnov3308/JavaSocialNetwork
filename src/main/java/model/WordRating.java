package main.java.model;

import java.util.Comparator;

public class WordRating {
    private String word;
    private Integer rating;

    public WordRating(String word) {
        this.word = word;
        this.rating = 1;
    }

    public String getWord() {
        return word;
    }

    public Integer getRating() {
        return rating;
    }

    public void incRating() {
        this.rating++;
    }

    public static final Comparator<WordRating> COMPARE_BY_RATING = new Comparator<WordRating>() {
        @Override
        public int compare(WordRating o1, WordRating o2) {
            return o1.getRating() - o2.getRating();
        }
    };
}
