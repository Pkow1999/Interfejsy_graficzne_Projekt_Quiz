package com.example.project;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerAddQuestion {
    @FXML
    private Label questionText;

    @FXML
    private MenuButton category;
    @FXML
    private MenuItem POLISH,HISTORY,ENGLISH,MATH;

    private Parent home_page_parent;
    private Scene home_page_scene;
    private Stage stage;
    @FXML
    protected void onReturnButtonClick(ActionEvent event) throws IOException {
        //to jest czek ktory sprawdza czy ładuje view zalogowanego czy nie
        if (!ControllerMain.getLogOn())
            home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));//to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej
       else home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainLogged-view.fxml")));//to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej

        //TODO
        home_page_scene =  new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        stage.setTitle("Quizowanie!");
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
    }
    @FXML
    protected void onEnterQButtonClick(ActionEvent event) throws IOException {
        stage = new Stage();//robimy nowe okienko
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
        Scene home_page_scene = new Scene(fxmlLoader.load());//jak robi sie nowa scene to tworzy sie nowe okno - mozna to uzyc przy historii
        stage.setScene(home_page_scene);
        stage.setResizable(false);
        stage.show();
    }

    public void onExitButtonClick(ActionEvent event) {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
