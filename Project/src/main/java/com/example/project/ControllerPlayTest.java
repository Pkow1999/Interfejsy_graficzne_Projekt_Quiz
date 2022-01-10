package com.example.project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class ControllerPlayTest  {
    @FXML
    private Label questionText;
    private Parent home_page_parent;
    private Scene home_page_scene;
    private Stage stage;



    @FXML
    private Label timerLabel;
    private static final Integer STARTTIME=20;
    private static Integer time;
    private Timeline timeline;

    public ControllerPlayTest()
    {
        timerLabel = new Label();
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    @FXML
    public void initialize()
    {
        time = STARTTIME;
        timerLabel.setText(time.toString());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            time--;
            timerLabel.setText(time.toString());
            if(time < 1) {
                timeline.stop();
            }
            if(home_page_parent != null)
                timeline.stop();
        }));
        timeline.playFromStart();

    }
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
        if (!ControllerMain.getLogOn())
            home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));//to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej
        else home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));//to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej

        //to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej(CHYBA!!)
        //TODO
        home_page_scene =  new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        if(!ControllerMain.getLogOn())
            stage.setTitle("Quizowanie!");
        else stage.setTitle("Quizowanie - Zalogowany!");
        stage.setResizable(false);
        stage.show();
    }
}
