package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**Klasa obsługująca okienko popUp z błedem wyboru rodzaju gry*/
public class ControllerSelectGameModePopUp {
    public Pane background;
    @FXML
    private Text popUpText;
    /**Metoda inijcalizująca*/
    @FXML
    public void initialize()//initialize dziala PO konstruktorze i PO FXMLu ALE PRZED showem
    {
        popUpText.setText(ControllerSelectGameMode.errorChecking());
        if(ControllerSettings.backgroundColour == 1)
        {
            background.setStyle("-fx-background-color: dimgray");
            popUpText.setStyle("-fx-background-color: black");
        }
    }
    /**Metoda obsługująca zamknięcie okna*/
    @FXML
    public void onExitButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
