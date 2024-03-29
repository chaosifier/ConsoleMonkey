package com.consolemonkey.genarator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SentenceGenerator {
    private Map<String, List<String>> wordMap;
    private Random random;
    private boolean trained;

    public SentenceGenerator() {
        wordMap = new HashMap<>();
        random = new Random();
    }

    public void train(String text) {
        String[] words = text.split("\\s+");
        for (int i = 0; i < words.length - 1; i++) {
            String currentWord = words[i];
            String nextWord = words[i + 1];
            if (!wordMap.containsKey(currentWord)) {
                wordMap.put(currentWord, new ArrayList<>());
            }
            wordMap.get(currentWord).add(nextWord);
        }
        trained = true;
    }

    public String generateSentence(int length) {
        List<String> sentence = new ArrayList<>();
        String startWord = getRandomStartingWord();
        sentence.add(startWord);
        for (int i = 1; i < length; i++) {
            if (!wordMap.containsKey(startWord)) {
                break;
            }
            List<String> nextWords = wordMap.get(startWord);
            String nextWord = nextWords.get(random.nextInt(nextWords.size()));
            sentence.add(nextWord);
            startWord = nextWord;
        }
        sentence = sentence.stream().limit(length).toList();
        return String.join(" ", sentence);
    }

    private String getRandomStartingWord() {
        List<String> keysAsArray = new ArrayList<>(wordMap.keySet());
        return keysAsArray.get(random.nextInt(keysAsArray.size()));
    }

    // public static void main(String[] args) {
    //     SentenceGenerator generator = new SentenceGenerator();
    //     generator.train("The quick brown fox jumps over the lazy dog");
    //     generator.train("The lazy dog sleeps");
    //     generator.train("sleeps walking endlessly dreaming");
    //     generator.train("fox gold money bitcoin");

    //     // Generate a random sentence
    //     String randomSentence = generator.generateSentence(8);
    //     System.out.println("Random Sentence: " + randomSentence);
    // }

    public boolean isTrained() {
        return trained;
    }
}
