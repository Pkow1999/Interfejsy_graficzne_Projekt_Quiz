package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerPlayTest {
    @FXML
    private Label questionText;

    private Parent home_page_parent;
    private Scene home_page_scene;
    private Stage stage;

    @FXML
    protected void onHello1ButtonClick() {questionText.setText("1");}
    @FXML
    protected void onHello2ButtonClick() {questionText.setText("2");}
    @FXML
    protected void onHello3ButtonClick() {questionText.setText("3");}
    @FXML
    protected void onHello4ButtonClick() {questionText.setText("4");}
    @FXML
    protected void onExitButtonClick(ActionEvent event) throws IOException {
        //to jest czek ktory sprawdza czy ładuje view zalogowanego czy nie
        if (ControllerMain.logOn == false)
            home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));//to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej
        else home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainLogged-view.fxml")));//to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej

        //to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej(CHYBA!!)
        //TODO
        home_page_scene =  new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        stage.setTitle("Quizowanie!");
        stage.setResizable(false);
        stage.show();
    }
}
