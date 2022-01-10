package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerSummary {
    @FXML
    public Label question1;
    public Label question2;
    public Label question3;
    public Label question4;
    public Label question5;
    public Label question6;
    public Label question7;
    public Label question8;
    public Label question9;
    public Label question10;
    public Label percentageLabel;
    public Label punctationLabel;
    public Label timeLabel;

    @FXML
    public void initialize() {
        if (ControllerPlayTest.AnswerQuestion[0]) {
            question1.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            question1.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }


        if (ControllerPlayTest.AnswerQuestion[1]) {
            question2.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            question2.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }


        if (ControllerPlayTest.AnswerQuestion[2]) {
            question3.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            question3.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }


        if (ControllerPlayTest.AnswerQuestion[3]) {
            question4.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            question4.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }


        if (ControllerPlayTest.AnswerQuestion[4]) {
            question5.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            question5.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }


        if (ControllerPlayTest.AnswerQuestion[5]) {
            question6.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            question6.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }


        if (ControllerPlayTest.AnswerQuestion[6]) {
            question7.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            question7.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }


        if (ControllerPlayTest.AnswerQuestion[7]) {
            question8.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            question8.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }


        if (ControllerPlayTest.AnswerQuestion[8]) {
            question9.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        } else {
            question9.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(ControllerPlayTest.AnswerQuestion[9])
        {
            question10.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY , Insets.EMPTY)));
        }
        else {
            question10.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY , Insets.EMPTY)));
        }
        int counter = 0;
        for(int i = 0;i<10;i++)
            if(ControllerPlayTest.AnswerQuestion[i])
                counter++;
            percentageLabel.setText(counter * 10 +"%");
            timeLabel.setText(String.valueOf(Questions.timeAll)+" sek.");
            punctationLabel.setText(String.valueOf(Questions.punctation)+" pkt.");
    }

    public void ButtonExitClick(ActionEvent event) throws IOException {
        Parent home_page_parent;
        if (!ControllerMain.getLogOn())
            home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));//to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej
        else home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));//to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej

        //TODO
        Scene home_page_scene = new Scene(home_page_parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        if(!ControllerMain.getLogOn())
            stage.setTitle("Quizowanie!");
        else stage.setTitle("Quizowanie - Zalogowany!");
        stage.show();
    }
}
