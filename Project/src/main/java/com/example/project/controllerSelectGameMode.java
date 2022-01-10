package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class controllerSelectGameMode {

    private Parent home_page_parent;
    private Scene home_page_scene;
    private Stage stage;

    @FXML
    private boolean gameMode;
    @FXML
    private int intLevelOfDifficulty;
    @FXML
    private ToggleGroup mode;
    @FXML
    private ToggleGroup levelOfDifficulty;
    @FXML
    private MenuButton category;
    @FXML
    private MenuItem POLISH,HISTORY,ENGLISH,MATH;
    static private Boolean[] errors = {true, false, false};//tablica posiadajaca errory mozliwe w dodawaniu pytania
    //jeden jest od razu na true bo jest to kategoria i zmieni sie jak ustawimy dowolna kategorie z mozliwych
    @FXML
    private RadioButton test, nauka, szkolaPodstawowa, szkolaSrednia, powtorkaDoMatury;

    public void onModeRadioClick(ActionEvent event) {
        if(test.isSelected()) {
            gameMode = true;

        }
        else if(nauka.isSelected()) {
            gameMode = false;
        }
    }

    public void onLevelOfDifficultyRadioClick(ActionEvent event) {
        if(szkolaPodstawowa.isSelected()) {
            intLevelOfDifficulty = 0;
        }
        else if(szkolaSrednia.isSelected()) {
            intLevelOfDifficulty = 1;
        }
        else if(powtorkaDoMatury.isSelected()) {
            intLevelOfDifficulty = 2;
        }
    }

    @FXML
    protected void ChangeCategory(ActionEvent event) {
        //powinno dac rade to zrobic bardziej elegancko, ale nie mam teraz pojecia jak
        if(event.getTarget().equals(POLISH))
            category.setText(POLISH.getText());
        else if (event.getTarget().equals(HISTORY))
            category.setText(HISTORY.getText());
        else if (event.getTarget().equals(ENGLISH))
            category.setText(ENGLISH.getText());
        else if (event.getTarget().equals(MATH))
            category.setText(MATH.getText());
        errors[0] = false;
    }

    public void onPlayButtonClick(ActionEvent event) throws IOException {
        home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("question-view.fxml")));
        home_page_scene =  new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        stage.setTitle("Rozwiązywanie Quizu!");
        stage.show();
    }
}
