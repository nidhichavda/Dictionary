package com.example.dictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseDictionary {

    DatabaseConnection databaseConnection = new DatabaseConnection();
    public String getMeaning(String word)
    {
        String meaning = "";
        String meaningQuery = String.format("SELECT meaning FROM dict_table WHERE word = '%s'", word.toLowerCase());
       // System.out.println("after connection");
        try{
            ResultSet rs = databaseConnection.getQueryTable(meaningQuery);
            rs.next();
            meaning = rs.getString("meaning");

           // System.out.println(rs.getString("meaning"));

        }catch (Exception e)
        {
            e.getMessage();
        }
        return meaning;
    }

    public int addMeaning(String word, String meaning) {
//        System.out.println(meaning);
        String addMeaningQuery = String.format("INSERT into dict_table (word, meaning) VALUES ('%s','%s')", word.toLowerCase(),meaning.toLowerCase());

        return databaseConnection.addMeaningToTable(addMeaningQuery);
//        String  = "";
//        String meaningQuery = String.format("SELECT meaning FROM dict_table WHERE word = '%s'", word.toLowerCase());
//        // System.out.println("after connection");
//        try{
//            ResultSet rs = databaseConnection.getQueryTable(meaningQuery);
//            rs.next();
//            meaning = rs.getString("meaning");
//
//            // System.out.println(rs.getString("meaning"));
//
//        }catch (Exception e)
//        {
//            e.getMessage();
//        }
//        return meaning;
    }
}
