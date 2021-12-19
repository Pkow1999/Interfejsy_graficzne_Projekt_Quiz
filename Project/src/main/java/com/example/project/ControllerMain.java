package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.application.Platform;

public class ControllerMain {
    @FXML
    private Label welcomeText;


    @FXML
    protected void onPlayButtonClick() {
        welcomeText.setText("Zagrajmy noooo");
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