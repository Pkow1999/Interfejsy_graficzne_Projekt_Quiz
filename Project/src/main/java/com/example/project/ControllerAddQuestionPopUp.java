package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**Klasa obsługująca okienko z informacją na temat stanu aplikacji*/
public class ControllerAddQuestionPopUp {
    public Pane background;
    /**Napis informujący o stanie aplikacji*/
    @FXML
    private Text popUpText;
    /**Metoda inicjalizująca popUp który się pojawia po kliknięciu w zatwierdz pytanie*/
    @FXML
    public void initialize()//initialize dziala PO konstruktorze i PO FXMLu ALE PRZED showem
    {
        popUpText.setText(ControllerAddQuestion.errorChecking());
        if(ControllerSettings.backgroundColour == 1)
        {
            background.setStyle("-fx-background-color: dimgray");
            popUpText.setStyle("-fx-background-color: black");
        }
    }
    /**Metoda obsługująca zamknięcie okienka po naciśnięciu przycisku*/
    @FXML
    public void onExitButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
