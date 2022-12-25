package com.example.dictionary;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MyDictionary extends Application {

        Button searchButton;
        Button addNewWordButton;
        TextField wordTextField;
        Label infoLabel;
        TextField meaningTextField;
        ListView<String> suggestionList;

    DatabaseDictionary databaseDictionaryObj = new DatabaseDictionary();
    Pane createContent(){
        Pane root =new Pane();
        root.setPrefSize(500, 300);

        wordTextField = new TextField();
        wordTextField.setTranslateX(20);
        wordTextField.setTranslateY(30);

        infoLabel = new Label();
        infoLabel.setTranslateX(20);
        infoLabel.setTranslateY(60);

        meaningTextField = new TextField();
        meaningTextField.setTranslateX(20);
        meaningTextField.setTranslateY(80);
        meaningTextField.setMinWidth(100);
        meaningTextField.setMinHeight(50);
        meaningTextField.setVisible(false);
        meaningTextField.setPromptText("Please Enter the meaning");

        searchButton = new Button("Search");
        searchButton.setTranslateX(200);
        searchButton.setTranslateY(30);

        addNewWordButton = new Button("Add");
        addNewWordButton.setTranslateX(280);
        addNewWordButton.setTranslateY(30);
        addNewWordButton.setVisible(false);


//        searchButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
////                meaningLabel.setText("button clicked");
//                String word = wordTextField.getText();
//                if(word.isBlank() || word.isEmpty())
//                {
//                    meaningLabel.setText("please enter a word");
//                    meaningLabel.setTextFill(Color.RED);
//                }
//                else {
//                String meaning = DictionaryUsingHashMap.getMeaning(word);
//                meaningLabel.setText(meaning);
//                meaningLabel.setTextFill(Color.BLACK);
//                }
//            }
//        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String word = wordTextField.getText();

                String meaning = databaseDictionaryObj.getMeaning(word);

                if(meaning != "") {
                    infoLabel.setText(meaning);
                    infoLabel.setTextFill(Color.BLACK);
                }
                else {
                    infoLabel.setText("Meaning to this word is not found...Would like to add the word in the dictionary.");
                    infoLabel.setTextFill(Color.BLACK);
                    meaningTextField.setVisible(true);
                    addNewWordButton.setVisible(true);
                }
            }
        });

        addNewWordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String meaning = meaningTextField.getText();
                if(databaseDictionaryObj.addMeaning(wordTextField.getText(),meaning) != 0) {
                    infoLabel.setText("Word added successfully");
                    meaningTextField.setVisible(false);
                    addNewWordButton.setVisible(false);
                    wordTextField.clear();
                }
                else
                    infoLabel.setText("Word addition unsuccess");
            }
        });

        suggestionList = new ListView<>();
        suggestionList.setTranslateX(20);
        suggestionList.setTranslateY(100);
        suggestionList.setMinSize(330,50);
        suggestionList.setMaxSize(330,30);

        String[] wordList= {"anand", "vivek", "rahul", "subh"};
        suggestionList.getItems().addAll(wordList);
        suggestionList.setOrientation(Orientation.HORIZONTAL);

        suggestionList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectedWord = suggestionList.getSelectionModel().getSelectedItem();
            }
        });
        root.getChildren().addAll(wordTextField,searchButton,infoLabel,addNewWordButton,meaningTextField);

        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Dictionary!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}