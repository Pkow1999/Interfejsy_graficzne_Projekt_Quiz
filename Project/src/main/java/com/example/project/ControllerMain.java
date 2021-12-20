package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerMain {
    @FXML
    private Label welcomeText;

    private Parent home_page_parent;
    private Scene home_page_scene;
    private Stage stage;
    @FXML
    protected void onPlayButtonClick(ActionEvent event) throws IOException {
        home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("question-view.fxml")));
        home_page_scene =  new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        stage.show();
    }
    @FXML
    protected void onAddQuestionButtonClick(ActionEvent event) throws IOException {
        home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("new.fxml")));
        home_page_scene =  new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        stage.show();
    }
    @FXML
    protected void onCheckHistoryClick() {welcomeText.setText("Historii nie zmienisz.");}
    @FXML
    protected void onExitClick()
    {
        welcomeText.setText("Uciekasz przed obowiazkami!");
        Platform.exit();
    }
}