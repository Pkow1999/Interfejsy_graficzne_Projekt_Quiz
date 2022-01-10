package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerMain {
    private static boolean logOn = false;//zmienna ktora przechowywuje czy jestes zalogowany
    public static boolean getLogOn(){return logOn;}
    @FXML
    private Text LoginText;
    @FXML
    private Label welcomeText;

    private Parent home_page_parent;
    private Scene home_page_scene;
    private Stage stage;
    public ControllerMain()
    {
        LoginText = new Text();
    }
    @FXML
    public void initialize()
    {
        if(getLogOn())
        {
            LoginText.setText("Wyloguj:");
        }
        else
        {
            LoginText.setText("Zaloguj:");
        }

    }
    @FXML
    protected void onLoginClick(ActionEvent event) throws IOException {
        //Zamykamy stare okienko
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        if(!getLogOn()){
            stage = new Stage();//robimy nowe okienko z ekranem logowania
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Scene home_page_scene = new Scene(fxmlLoader.load());//jak robi sie nowa scene to tworzy sie nowe okno - mozna to uzyc przy historii
            stage.setScene(home_page_scene);
            stage.setResizable(false);
            stage.setTitle("Zaloguj się!");
            stage.show();
        }
        else {
            logOn = false;
            home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
            home_page_scene =  new Scene(home_page_parent);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(home_page_scene);
            stage.setTitle("Quizowanie!");
            stage.show();
        }
    }
    @FXML
    protected void onLoginClickedClick(ActionEvent event) throws IOException {
        logOn = true;
        //zamykamy okienko z logowaniem
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
        //otwieramy okienko main po zalogowaniu
        home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        home_page_scene =  new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        stage.setTitle("Quizowanie - Zalogowany!");
        stage.show();
    }


    @FXML
    protected void onBackClicked(ActionEvent event) throws IOException {
        //zamykamy okienko z logowaniem
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
        //otwieramy okienko main po zalogowaniu
        home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        home_page_scene =  new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        stage.setTitle("Quizowanie!");
        stage.show();
    }

    @FXML
    protected void onPlayButtonClick(ActionEvent event) throws IOException {
        home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("question-view.fxml")));
        home_page_scene =  new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        stage.setTitle("Rozwiązywanie Quizu!");
        stage.show();
    }
    @FXML
    protected void onAddQuestionButtonClick(ActionEvent event) throws IOException {
        home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addQuestion-view.fxml")));
        home_page_scene =  new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        stage.setTitle("Dodaj pytanie!");
        stage.show();
    }
    @FXML
    protected void onCheckHistoryClick() throws IOException {
        if(!getLogOn()){
            stage = new Stage();//robimy nowe okienko
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("notLoggedInPopUp.fxml"));
            Scene home_page_scene = new Scene(fxmlLoader.load());//jak robi sie nowa scene to tworzy sie nowe okno - mozna to uzyc przy historii
            stage.setScene(home_page_scene);
            stage.setResizable(false);
            stage.show();
        }
        else
        {
            welcomeText.setText("Otworzyć historie!");
        }
    }
    @FXML
    protected void onExitClick()
    {
        welcomeText.setText("Uciekasz przed obowiazkami!");
        Platform.exit();
    }

    public void onExitPopUpButtonClick(ActionEvent event) {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}