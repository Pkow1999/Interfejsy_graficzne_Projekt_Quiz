package com.example.project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;


//Trzeba te wszystkie metody poukladac i powsadzac w odrebne metody, pozamieniac zmienne na prywatne
public class ControllerPlayTest  {
    @FXML
    public Button Button2;
    @FXML
    public Button Button4;
    @FXML
    public Button Button1;
    @FXML
    public Button Button3;
    @FXML
    public ImageView exitImage;
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
    private ArrayList<Button> przyciski = new ArrayList<>();

    @FXML
    private Label timerLabel;
    private static final Integer STARTTIME=20;
    private static Integer time;
    private Timeline timeline;
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
        przyciski.add(Button1);
        przyciski.add(Button2);
        przyciski.add(Button3);
        przyciski.add(Button4);
        QuestionLabel.setText("Pytanie nr " + (Questions.getIndex() + 1) +"/10");
        if(ControllerSelectGameMode.kategoria == 1)
        {
            zasobnik = Questions.polskiPytania;
        }
        else if(ControllerSelectGameMode.kategoria == 2)
        {
            zasobnik = Questions.historiaPytania;
        }
        else if(ControllerSelectGameMode.kategoria == 3)
        {
            zasobnik = Questions.angielskiPytania;
        }
        else
        {
            zasobnik = Questions.matematykaPytania;
        }

        QuestionText.setText(zasobnik.get(Questions.getIndex())[0]);
        ArrayList<String> losu = new ArrayList<>();
        losu.add(zasobnik.get(Questions.getIndex())[1]);
        losu.add(zasobnik.get(Questions.getIndex())[2]);
        losu.add(zasobnik.get(Questions.getIndex())[3]);
        losu.add(zasobnik.get(Questions.getIndex())[4]);
        Collections.shuffle(losu);
        for(int i = 0;i<4;i++)
        {
            if(losu.get(i).length() > 18)
                przyciski.get(i).setStyle(przyciski.get(i).getStyle()+"-fx-font-size:20;");
            else if(losu.get(i).length() > 25)
                przyciski.get(i).setStyle(przyciski.get(i).getStyle()+"-fx-font-size:15;");
            else przyciski.get(i).setStyle(przyciski.get(i).getStyle()+"-fx-font-size:30;");
            przyciski.get(i).setText(losu.get(i));
        }


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
    protected void onAnswerButtonClick(ActionEvent event) throws IOException {
        timeline.stop();
        int timeCurrent = 20-time;
        Questions.addTime(timeCurrent);
        if(((Button)event.getSource()).getText().equals(zasobnik.get(Questions.getIndex())[1]))
        {
            Questions.increasePunctation(time);
            Questions.changeAnswer(Questions.getIndex());
        }
        Questions.incrementIndex();
        if(Questions.getIndex() > 9)
        {
            home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("summaryAlternative.fxml")));
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


        Questions.resetIndex();
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

    public void onMouseExitedButton(MouseEvent mouseEvent) {
        Button source = (Button) mouseEvent.getSource();
        source.setStyle(source.getStyle() + "-fx-border-width:1;");
    }

    public void onMouseOverButton(MouseEvent mouseEvent) {
        Button source = (Button) mouseEvent.getSource();
        source.setStyle(source.getStyle() + "-fx-border-width:2;");
    }

    public void onExitButtonEntered(MouseEvent mouseEvent) {
        exitImage.setStyle("-fx-opacity:1;");
    }

    public void onExitButtonExited(MouseEvent mouseEvent) {
        exitImage.setStyle("-fx-opacity:0.3;");

    }
}
