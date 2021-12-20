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

public class ControllerMain {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onPlayButtonClick(ActionEvent event) throws IOException {
        welcomeText.setText("Zagrajmy noooo");
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("question-view.fxml"));
        Scene home_page_scene = new Scene(home_page_parent, 320,240);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        stage.show();
    }
    @FXML
    protected void onAddQuestionButtonClick() {welcomeText.setText("Pytanie dodac chcesz hmmmm?");}
    @FXML
    protected void onCheckHistoryClick() {welcomeText.setText("Historii nie zmienisz.");}
    @FXML
    protected void onExitClick()
    {
        welcomeText.setText("Uciekasz przed obowiazkami!");
        Platform.exit();
    }
}