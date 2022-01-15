package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ControllerSummaryAlternative {
    @FXML
    private Button question1,question2,question3,question4,question5,question6,question7,question8,question9,question10;
    @FXML
    private Label percentageLabel;
    @FXML
    private Label punctationLabel;
    @FXML
    private Label timeLabel;

    public static int index;

    @FXML
    public void initialize() {
        int counter = 0;
        ArrayList<Button> questionListLabel = new ArrayList<>(Arrays.asList(question1,question2,question3,question4,question5,question6,question7,question8,question9,question10));
        //ustawienie dobrych odpowiedzi na kolor zielony oraz zwiekszenie licznika, a zlych na czerwony
        for(int i= 0;i<questionListLabel.size();i++)
        {
            if(Questions.getAnswer(i))
            {
                questionListLabel.get(i).setStyle("-fx-background-color: green; ");
                counter++;
            }
        }

        percentageLabel.setText(counter * 10 +"%");
        timeLabel.setText(Questions.getTime()+" sek.");
        punctationLabel.setText(Questions.getPunctation()+" pkt.");
    }

    public void ButtonExitClick(ActionEvent event) throws IOException {
        Parent home_page_parent;
        //to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej
        home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));//to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej

        //TODO
        Scene home_page_scene = new Scene(home_page_parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        if(!ControllerMain.getLogOn())
            stage.setTitle("Quizowanie!");
        else stage.setTitle("Quizowanie - Zalogowany!");
        stage.show();
    }

    public void ButtonQuestionClick(ActionEvent event) throws IOException{
        index = Integer.valueOf(((Button)event.getSource()).getText()) - 1;

        Stage stage = new Stage();//robimy nowe okienko z ekranem logowania
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popUp.fxml"));
        Scene home_page_scene = new Scene(fxmlLoader.load());//jak robi sie nowa scene to tworzy sie nowe okno - mozna to uzyc przy historii
        stage.setScene(home_page_scene);
        stage.setResizable(false);
        stage.setTitle("Pytanie nr:" +((Button)event.getSource()).getText());
        stage.show();
    }
    public static String questionContent(int index){
        if(ControllerSelectGameMode.kategoria == 1)
        {
            return Questions.polskiPytania.get(index)[0];
        }
        else if(ControllerSelectGameMode.kategoria == 2)
        {
            return Questions.historiaPytania.get(index)[0];
        }
        else if(ControllerSelectGameMode.kategoria == 3)
        {
            return Questions.angielskiPytania.get(index)[0];
        }
        else
        {
            return Questions.matematykaPytania.get(index)[0];
        }
    }
}
