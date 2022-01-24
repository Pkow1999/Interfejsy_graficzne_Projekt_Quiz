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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**Klasa obsługująca okno z rozwiązywaniem quizu*/
public class ControllerPlayTest  {
    /**Zmienne przechowywujace przyciski*/
    @FXML
    public Button Button1,Button2,Button3,Button4,exitButton;
    /***/
    @FXML
    private ImageView exitImage;
    @FXML
    private Pane background;
    @FXML
    private Rectangle numerRec,timerRec,questionRec;
    /***/
    @FXML
    private Label QuestionText;
    /***/
    @FXML
    private Label QuestionLabel;

    private Parent home_page_parent;
    private Scene home_page_scene;
    private Stage stage;
    /**Lista przechowywujaca wszystkie pytania przy pomocy polimorfizmmu*/
    private ArrayList<String[]> zasobnik;
    /**Lista przechowywująca przyciski z odpowiedziami*/
    private ArrayList<Button> przyciski = new ArrayList<>();
    /***/
    @FXML
    private Label timerLabel;
    /**Zmienna przechowywujaca początkowy czas naszego licznika*/
    private static final Integer STARTTIME=20;
    /**Zmienna przechowywujaca aktualny czas licznika*/
    private static Integer time;
    /**Zmienna pozwalająca nam na robienie animacji(w tym przypadku zmianę licznika) co dana klatkę kluczowa(w tym przypadku co sekundę) bez wymagania korzystania z wątków*/
    private Timeline timeline;
    /**Konstruktor*/
    public ControllerPlayTest()
    {
        exitButton = new Button();
        timerLabel = new Label();
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    /**Metoda inicjalizująca*/
    @FXML
    public void initialize() {
        przyciski.add(Button1);
        przyciski.add(Button2);
        przyciski.add(Button3);
        przyciski.add(Button4);

        ArrayList<Rectangle> rectangleArrayList = new ArrayList<>();
        rectangleArrayList.add(numerRec);
        rectangleArrayList.add(questionRec);
        rectangleArrayList.add(timerRec);

        ArrayList<Label> labelArrayList = new ArrayList<>();
        labelArrayList.add(timerLabel);
        labelArrayList.add(QuestionLabel);
        labelArrayList.add(QuestionText);

        if(ControllerSettings.backgroundColour == 1)
        {
            background.setStyle("-fx-background-color: darkslategray");
            for(Button przycisk : przyciski)
            {
                przycisk.setStyle("-fx-background-radius: 10; -fx-background-color: DimGrey; -fx-border-color: beige; -fx-border-radius: 10; -fx-text-fill:black;");
            }
            for(Rectangle rec : rectangleArrayList)
            {
                rec.setStyle(rec.getStyle() + "-fx-fill: #556b2f;");
            }
            for(Label lejbel : labelArrayList)
            {
                lejbel.setStyle("-fx-text-fill:black;");
            }
        }
        QuestionLabel.setText("Pytanie nr " + (Questions.getIndex() + 1) +"/10");
        if(ControllerSelectGameMode.kategoria == 1 && ControllerSelectGameMode.intLevelOfDifficulty == 0)
        {
            zasobnik = Questions.polskiPytaniaEasy;
        }
        else if (ControllerSelectGameMode.kategoria == 1 && ControllerSelectGameMode.intLevelOfDifficulty == 1)
        {
            zasobnik = Questions.polskiPytaniaMed;
        }
        else if (ControllerSelectGameMode.kategoria == 1 && ControllerSelectGameMode.intLevelOfDifficulty == 2)
        {
            zasobnik = Questions.polskiPytaniaHard;
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
        if(zasobnik.get(Questions.getIndex())[0].length() > 80)
            QuestionText.setStyle(QuestionText.getStyle() + "-fx-font-size:30;");
        else
            QuestionText.setStyle(QuestionText.getStyle() + "-fx-font-size:40;");

        QuestionText.setText(zasobnik.get(Questions.getIndex())[0]);
        ArrayList<String> losu = new ArrayList<>();
        losu.add(zasobnik.get(Questions.getIndex())[1]);
        losu.add(zasobnik.get(Questions.getIndex())[2]);
        losu.add(zasobnik.get(Questions.getIndex())[3]);
        losu.add(zasobnik.get(Questions.getIndex())[4]);
        Collections.shuffle(losu);

        for(int i = 0;i<4;i++)
        {
            if(losu.get(i).length() > 17)
                przyciski.get(i).setStyle(przyciski.get(i).getStyle()+"-fx-font-size:20;");
            else if(losu.get(i).length() > 24)
                przyciski.get(i).setStyle(przyciski.get(i).getStyle()+"-fx-font-size:15;");
            else if(losu.get(i).length() > 29)
                przyciski.get(i).setStyle(przyciski.get(i).getStyle()+"-fx-font-size:10;");
            else przyciski.get(i).setStyle(przyciski.get(i).getStyle()+"-fx-font-size:30;");
            przyciski.get(i).setText(losu.get(i));
        }


        time = STARTTIME;
        timerLabel.setText(time.toString());
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        //HEJ wyrażenie lambda! które obsługuje nam zmiane licznika co sekunde!
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            time--;
            timerLabel.setText(time.toString());
            if(time < 1) {
                timeline.stop();

                Questions.addTime(20);
                Questions.odpowiedzi.add("");//wsadzamy zla odpowiedz do listy aby pozniej ja wyswietlic w podsumowaniu

                Questions.incrementIndex();
                if(Questions.getIndex() > 9)
                {
                    try {
                        home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("summaryAlternative.fxml")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    home_page_scene =  new Scene(home_page_parent);
                    stage = (Stage) Button1.getScene().getWindow();
                    stage.setScene(home_page_scene);
                    stage.show();
                }
                else
                {
                    try {
                        home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("question-view.fxml")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    home_page_scene =  new Scene(home_page_parent);
                    stage = (Stage) Button1.getScene().getWindow();
                    stage.setScene(home_page_scene);
                    stage.show();
                }
            }
            if(home_page_parent != null)
                timeline.stop();
        }));
        timeline.playFromStart();
    }
    /**Metoda obsługująca kliknięcie w daną odpowiedz przy pytaniu*/
    @FXML
    protected void onAnswerButtonClick(ActionEvent event) throws IOException {
        timeline.stop();//zatrzymujemy timer
        int timeCurrent = 20-time;//odcinamy ile czasu minelo
        Questions.addTime(timeCurrent);
        if(((Button)event.getSource()).getText().equals(zasobnik.get(Questions.getIndex())[1]))//sprawdzamy czy odpowiedz jest poprawna
        {
            Questions.increasePunctation(time);
            Questions.changeAnswer(Questions.getIndex());
        }
        Questions.odpowiedzi.add(((Button)event.getSource()).getText());//wsadzamy dana odpowiedz (nawet jak jest zla) to listy aby pozniej ja wyswietlic w podsumowaniu
        Questions.incrementIndex();
        if(Questions.getIndex() > 9)//sprawdzamy czy to ostatnie pytanie
        {
            home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("summaryAlternative.fxml")));
            home_page_scene =  new Scene(home_page_parent);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(home_page_scene);
            stage.show();
        }
        else//jak nie lecimy dalej
        {
            home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("question-view.fxml")));
            home_page_scene =  new Scene(home_page_parent);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(home_page_scene);
            stage.show();
        }
    }
    /**Metoda obsługująca cofnięcie sie do ekranu głównego*/
    @FXML
    protected void onExitButtonClick(ActionEvent event) throws IOException {


        Questions.resetIndex();

        home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));//to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej


        home_page_scene =  new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);

        if(!ControllerMain.getLogOn())
            stage.setTitle("Quizowanie!");
        else stage.setTitle("Quizowanie - Zalogowany!");
        stage.setResizable(false);
        stage.show();

    }
    /**Metoda obsługujaca najechanie myszki na odpowiedz*/
    public void onMouseExitedButton(MouseEvent mouseEvent) {
        Button source = (Button) mouseEvent.getSource();
        source.setStyle(source.getStyle() + "-fx-border-width:1;");
    }
    /**Metoda obsługująca odjechanie myszki z odpowiedzi*/
    public void onMouseOverButton(MouseEvent mouseEvent) {
        Button source = (Button) mouseEvent.getSource();
        source.setStyle(source.getStyle() + "-fx-border-width:2;");
    }
    /**Metoda obsługująca najechanie myszki na przycisk powrotu*/
    public void onExitButtonEntered(MouseEvent mouseEvent) {
        exitImage.setStyle("-fx-opacity:1;");
    }
    /**Metoda obslugująca odjechanie myszki z przycisku powrotu*/
    public void onExitButtonExited(MouseEvent mouseEvent) {
        exitImage.setStyle("-fx-opacity:0.3;");

    }
}
