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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;



//Trzeba te wszystkie metody poukladac i powsadzac w odrebne metody, pozamieniac zmienne na prywatne
public class ControllerPlayTest  {
    static public boolean[] AnswerQuestion = {false, false, false, false, false, false, false, false, false, false};
    @FXML
    public Button Button2;
    @FXML
    public Button Button4;
    @FXML
    public Button Button1;
    @FXML
    public Button Button3;
    @FXML
    private Label QuestionText;
    @FXML
    private Button exitButton;
    @FXML
    private Label QuestionLabel;
    private Parent home_page_parent;
    private Scene home_page_scene;
    private Stage stage;

    private ArrayList<String[]> zasobnik;
    @FXML
    private Label timerLabel;
    private static final Integer STARTTIME=20;
    private static Integer time;
    private Timeline timeline;

    public static int timeAll;
    public ControllerPlayTest()
    {
        exitButton = new Button();
        timerLabel = new Label();
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    @FXML
    public void initialize()
    {
        QuestionLabel.setText("Pytanie nr " +String.valueOf(Questions.questionIndex+1)+"/10");
        if(controllerSelectGameMode.kategoria == 1)
        {
            zasobnik = Questions.polskiPytania;
        }
        else if(controllerSelectGameMode.kategoria == 2)
        {
            zasobnik = Questions.historiaPytania;
        }
        else if(controllerSelectGameMode.kategoria == 3)
        {
            zasobnik = Questions.angielskiPytania;
        }
        else
        {
            zasobnik = Questions.matematykaPytania;
        }
        QuestionText.setText(zasobnik.get(Questions.questionIndex)[0]);
        ArrayList<String> losu = new ArrayList<>();
        losu.add(zasobnik.get(Questions.questionIndex)[1]);
        losu.add(zasobnik.get(Questions.questionIndex)[2]);
        losu.add(zasobnik.get(Questions.questionIndex)[3]);
        losu.add(zasobnik.get(Questions.questionIndex)[4]);
        Collections.shuffle(losu);
        Button1.setText(losu.get(0));
        Button2.setText(losu.get(1));
        Button3.setText(losu.get(2));
        Button4.setText(losu.get(3));


        time = STARTTIME;
        timerLabel.setText(time.toString());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            System.out.println("Licznik");
            System.out.println(home_page_parent);
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
    protected void onHello1ButtonClick(ActionEvent event) throws IOException {
        timeline.stop();
        int timeCurrent = 20-time;
        Questions.timeAll += timeCurrent;
        Questions.punctation += 54*timeCurrent;
        if(((Button)event.getSource()).getText().equals(zasobnik.get(Questions.questionIndex)[1]))
        {
            AnswerQuestion[Questions.questionIndex] = true;
        }
        Questions.questionIndex++;
        if(Questions.questionIndex > 9)
        {
            int counter = 0;
            for(int i = 0 ; i < 10; i++)
            {
                if(AnswerQuestion[i])
                    counter++;
            }
            System.out.println(counter);
            home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("summary.fxml")));
            home_page_scene =  new Scene(home_page_parent);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(home_page_scene);
            stage.show();
        }
        else
        {
            home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("question-view.fxml")));
            home_page_scene =  new Scene(home_page_parent);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(home_page_scene);
            stage.show();
        }
    }
    @FXML
    protected void onExitButtonClick(ActionEvent event) throws IOException {


        Questions.questionIndex = 0;
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
