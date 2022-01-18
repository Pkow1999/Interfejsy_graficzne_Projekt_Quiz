package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerSettings {
    @FXML
    private MenuItem Bright,Dark,Gradient;
    @FXML
    private ImageView exitImage;
    @FXML
    private MenuButton Background;
    @FXML
    private Label numberCompletedQuiz;
    @FXML
    private TextField loginField;
    @FXML
    public void initialize()
    {
        loginField.setText(ControllerMain.loginName);
        numberCompletedQuiz.setText(String.valueOf(ControllerSummaryAlternative.numbers));
    }
    /**Metoda obsługująca najechanie myszki na przycisk powrotu*/
    public void onExitButtonExited(MouseEvent mouseEvent) {exitImage.setStyle("-fx-opacity:0.3;"); }
    /**Metoda obsługująca odjechanie myszki z przycisku powrotu*/
    public void onExitButtonEntered(MouseEvent mouseEvent) {exitImage.setStyle("-fx-opacity:1;");}
    /**Metoda obsługująca powrót do menu głównegos*/
    public void onReturnButtonClick(ActionEvent event) throws IOException {
        Parent home_page_parent;
        home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        if(!ControllerMain.getLogOn())
            stage.setTitle("Quizowanie!");
        else stage.setTitle("Quizowanie - Zalogowany!");
        stage.show();
    }
    /**Metoda obsługująca zatwierdzenie zmian*/
    public void onEnterQButtonClick(ActionEvent event) {
        ControllerMain.loginName = loginField.getText();
    }
    /**Metoda obsługujaca zmiane motywu*/
    public void ChangeBackground(ActionEvent event) {
        if(event.getTarget().equals(Dark))
            Background.setText(Dark.getText());
        else if (event.getTarget().equals(Bright))
            Background.setText(Bright.getText());
        else if (event.getTarget().equals(Gradient))
            Background.setText(Gradient.getText());
    }
}
