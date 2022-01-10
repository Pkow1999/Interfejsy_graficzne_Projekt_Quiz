package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class ControllerAddQuestion {
    @FXML
    private TextArea textArea;
    @FXML
    private TextField Good,Bad1,Bad2,Bad3;//textfieldy zawierajacy odpowiedzi
    @FXML
    private MenuButton category;
    @FXML
    private MenuItem POLISH,HISTORY,ENGLISH,MATH;
    static private Boolean[] errors = {true, true, true};//tablica posiadajaca errory mozliwe w dodawaniu pytania
    //jeden jest od razu na true bo jest to kategoria i zmieni sie jak ustawimy dowolna kategorie z mozliwych
    private Parent home_page_parent;
    private Scene home_page_scene;
    private Stage stage;

    @FXML
    protected void onReturnButtonClick(ActionEvent event) throws IOException {
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
    @FXML
    protected void onEnterQButtonClick(ActionEvent event) throws IOException {
        stage = new Stage();//robimy nowe okienko
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addQuestionPopUp.fxml"));
        Scene home_page_scene = new Scene(fxmlLoader.load());//jak robi sie nowa scene to tworzy sie nowe okno - mozna to uzyc przy historii
        stage.setScene(home_page_scene);
        stage.setResizable(false);
        stage.show();
        System.out.println(String.valueOf(errors[0]));
        System.out.println(String.valueOf(errors[1]));
        System.out.println(String.valueOf(errors[2]));
    }
    public static String errorChecking()//funkcja zwracajaca string z wiadomoscia do popupa o mozliwym bledzie
    {
        if(errors[0])
            return "Proszę, wybrać kategorię";
        else if(errors[1])
            return "Proszę, uzupełnić odpowiedzi";
        else if(errors[2])
            return "Proszę, uzupełnić pytanie";
        else return "Pytanie zostało wysłane do zatwierdzenia";
    }

    @FXML
    private void onReturnTextClick(KeyEvent keyEvent) {//metoda ktora sprawdza czy pola zostaly uzupelnione PO puszczeniu przycisku
        //bez tej funkcji moga pojawic sie opoznienia i bedzie trzeba naprzyklad kliknac w nowy textfield albo w okno aby dane zostaly zaakceptowane
        if(Good.getText().equals("") || Bad1.getText().equals("") || Bad2.getText().equals("") || Bad3.getText().equals(""))//sprawdzamy czy wszystkie odpowiedzi maja cos w sobie
        {
            errors[1] = true;
        }
        else errors[1] = false;
        if(textArea.getText().equals(""))//sprawdzamy czy w pytaniu nie ma pustego stringa
        {
            errors[2] = true;
        } else errors[2] = false;
    }
}
