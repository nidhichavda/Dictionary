package com.example.dictionary;
import java.util.HashMap;

public class DictionaryUsingHashMap {
    private static HashMap<String,String> dictionary;

    public DictionaryUsingHashMap() {
        this.dictionary = new HashMap<>();
        addWordList();
    }
    public boolean addWords(String word, String meaning)
    {
        try {
            dictionary.put(word,meaning);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;

    }
    public static String getMeaning(String word)
    {
        if(dictionary.containsKey(word))
        {
            return dictionary.get(word);
        }
        else {
            return "word does not exists in dictionary";
        }

    }

    private void addWordList()
    {
        addWords("shubh", "Auspicious");
        addWords("Phase", "A stage in the development of something");
        addWords("project","an Individual or collaborative enterprise that is  carefully");
    }
}

