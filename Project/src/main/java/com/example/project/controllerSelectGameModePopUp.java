package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class controllerSelectGameModePopUp {
    @FXML
    private Text popUpText;
    private Stage stage;
    @FXML
    public void initialize()//initialize dziala PO konstruktorze i PO FXMLu ALE PRZED showem
    {
        popUpText.setText(controllerSelectGameMode.errorChecking());
    }
    @FXML
    public void onExitButtonClick(ActionEvent event) {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}