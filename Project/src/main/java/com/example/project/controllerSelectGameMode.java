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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class controllerSelectGameMode {

    private Parent home_page_parent;
    private Scene home_page_scene;
    private Stage stage;


    public static int kategoria = 0;

    @FXML
    public boolean gameMode;
    @FXML
    public int intLevelOfDifficulty;
    @FXML
    private ToggleGroup mode;
    @FXML
    private ToggleGroup levelOfDifficulty;
    @FXML
    private MenuButton category;
    @FXML
    private MenuItem POLISH,HISTORY,ENGLISH,MATH;
    static private Boolean[] errors = {true, true, true};//tablica posiadajaca errory mozliwe w dodawaniu pytania
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
        errors[0] = false;
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
        errors[1] = false;
    }

    @FXML
    protected void ChangeCategory(ActionEvent event) {
        //powinno dac rade to zrobic bardziej elegancko, ale nie mam teraz pojecia jak
        if(event.getTarget().equals(POLISH)){
            category.setText(POLISH.getText());
            kategoria = 1;
        }
        else if (event.getTarget().equals(HISTORY)) {
            category.setText(HISTORY.getText());
            kategoria = 2;
        }
        else if (event.getTarget().equals(ENGLISH)) {
            category.setText(ENGLISH.getText());
            kategoria = 3;
        }
        else if (event.getTarget().equals(MATH)) {
            category.setText(MATH.getText());
            kategoria = 4;
        }
        errors[2] = false;
    }

    public void onPlayButtonClick(ActionEvent event) throws IOException {
        boolean wait = false;
        for (Boolean error : errors) {
            if (error) {
                wait = true;
                break;
            }
        }
        if(wait) {
            stage = new Stage();//robimy nowe okienko
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selectGameModePopUp.fxml"));
            Scene home_page_scene = new Scene(fxmlLoader.load());//jak robi sie nowa scene to tworzy sie nowe okno - mozna to uzyc przy historii
            stage.setScene(home_page_scene);
            stage.setResizable(false);
            stage.show();
        }
        else {
            home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("question-view.fxml")));
            home_page_scene =  new Scene(home_page_parent);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(home_page_scene);
            stage.setTitle("Rozwiązywanie Quizu!");
            stage.show();
        }
    }

    public void onReturnButtonClick(ActionEvent event) throws IOException {
        //to jest czek ktory sprawdza czy ładuje view zalogowanego czy nie
        if (!ControllerMain.getLogOn())
            home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));//to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej
        else home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));//to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej

        //TODO
        home_page_scene =  new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        if(!ControllerMain.getLogOn())
            stage.setTitle("Quizowanie!");
        else stage.setTitle("Quizowanie - Zalogowany!");
        stage.show();
    }

    public static String errorChecking()//funkcja zwracajaca string z wiadomoscia do popupa o mozliwym bledzie
    {
        if(errors[0])
            return "Proszę wybrać tryb gry";
        else if(errors[1])
            return "Proszę wybrać poziom trudności";
        else if(errors[2])
            return "Proszę, wybrać kategorię";
        else return "Pytanie zostało wysłane do zatwierdzenia";
    }
}
