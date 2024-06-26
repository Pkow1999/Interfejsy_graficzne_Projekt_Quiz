package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
/**Klasa obsługująca okno wybierania kategorii, trybu, jak i poziomu trudności*/
public class ControllerSelectGameMode {

    private Parent home_page_parent;
    private Scene home_page_scene;
    private Stage stage;

    /**Zmienna przechowywująca aktualnie wybraną kategorię*/
    public static int kategoria = 0;


    @FXML
    private Label wybierzGre;
    @FXML
    private Label wybierzTrudnosc;
    @FXML
    private Label wybierzKategorie;
    @FXML
    private Pane background;
    @FXML
    private Button playButton;
    @FXML
    public boolean gameMode;
    public static int intLevelOfDifficulty;
    @FXML
    private ToggleGroup mode;
    @FXML
    private ToggleGroup levelOfDifficulty;
    @FXML
    private MenuButton category;
    @FXML
    private MenuItem POLISH,HISTORY,ENGLISH,MATH;
    @FXML
    private ImageView returnImage;
    /**Tablica przechowywująca błędy w wybieraniu gry*/
    static private Boolean[] errors;//tablica posiadajaca errory mozliwe w dodawaniu pytania
    @FXML
    private RadioButton test, nauka, szkolaPodstawowa, szkolaSrednia, powtorkaDoMatury;

    /**Metoda inicjalizująca*/
    @FXML
    public void initialize()
    {
        errors = new Boolean[]{true, true, true};
        ArrayList<RadioButton> radioButtonArrayList = new ArrayList<>();
        radioButtonArrayList.add(test);
        radioButtonArrayList.add(nauka);
        radioButtonArrayList.add(szkolaPodstawowa);
        radioButtonArrayList.add(szkolaSrednia);
        radioButtonArrayList.add(powtorkaDoMatury);

        ArrayList<Label> labelArrayList = new ArrayList<>();
        labelArrayList.add(wybierzGre);
        labelArrayList.add(wybierzTrudnosc);
        labelArrayList.add(wybierzKategorie);

        if(ControllerSettings.backgroundColour == 1)
        {
            background.setStyle("-fx-background-color: darkslategray");
            playButton.setStyle(
                    "-fx-background-color: #696969;\n" +
                            "    -fx-background-insets: 0,1,2,3;\n" +
                            "    -fx-background-radius: 3,2,2,2;\n" +
                            "    -fx-text-fill: Black;\n" +
                            "    -fx-font-size: 30px;");
            for(RadioButton redio : radioButtonArrayList)
            {
                redio.setStyle("-fx-text-fill: BLACK");
            }
            for(Label lejbel : labelArrayList)
            {
                lejbel.setStyle("-fx-text-fill: BLACK");
            }
        }
    }
    /**Metoda obsługująca wyboru trybu gry*/
    public void onModeRadioClick(ActionEvent event) {
        if(test.isSelected()) {
            gameMode = true;

        }
        else if(nauka.isSelected()) {
            gameMode = false;
        }
        errors[0] = false;

    }
    /**Metoda obsługująca wyboru poziomu trudności*/
    public void onLevelOfDifficultyRadioClick(ActionEvent event) {
        if(szkolaPodstawowa.isSelected()) {
            intLevelOfDifficulty = 0;
        }
        else if(szkolaSrednia.isSelected()) {
            intLevelOfDifficulty = 1;
        }
        else if(powtorkaDoMatury.isSelected()) {
            intLevelOfDifficulty = 2;
        }
        errors[1] = false;
    }
    /**Metoda obsługująca zmianę kategorii gry*/
    @FXML
    protected void ChangeCategory(ActionEvent event) {
        //powinno dac rade to zrobic bardziej elegancko, ale nie mam teraz pojecia jak
        if(event.getTarget().equals(POLISH)){
            category.setText(POLISH.getText());
            kategoria = 1;
        }
        else if (event.getTarget().equals(HISTORY)) {
            category.setText(HISTORY.getText());
            kategoria = 2;
        }
        else if (event.getTarget().equals(ENGLISH)) {
            category.setText(ENGLISH.getText());
            kategoria = 3;
        }
        else if (event.getTarget().equals(MATH)) {
            category.setText(MATH.getText());
            kategoria = 4;
        }
        errors[2] = false;
    }
    /**Metoda obsługująca chęci wypełnienia quizu*/
    public void onPlayButtonClick(ActionEvent event) throws IOException {
        boolean wait = false;
        for (boolean error : errors) {
            if (error) {
                wait = true;
                break;
            }
        }
        if(wait) {
            stage = new Stage();//robimy nowe okienko
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selectGameModePopUp.fxml"));
            Scene home_page_scene = new Scene(fxmlLoader.load());//jak robi sie nowa scene to tworzy sie nowe okno - mozna to uzyc przy historii
            stage.setScene(home_page_scene);
            stage.setResizable(false);
        }
        else {
            Questions.resetIndex();
            Questions.resetTime();
            Questions.resetPunctation();
            Questions.resetAnswers();
            Questions.odpowiedzi.clear();
            Questions.gamemode = gameMode;
            if(gameMode)
                home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("question-view.fxml")));
            else
                home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("questionNauka-view.fxml")));
            home_page_scene =  new Scene(home_page_parent);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(home_page_scene);
            stage.setTitle("Rozwiązywanie Quizu!");
        }
        stage.show();
    }
    /**Metoda obsługująca powrót do okna głównego*/
    public void onReturnButtonClick(ActionEvent event) throws IOException {
        //to jest czek ktory sprawdza czy ładuje view zalogowanego czy nie
        //to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej
        home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));//to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej

        //TODO
        home_page_scene =  new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        if(!ControllerMain.getLogOn())
            stage.setTitle("Quizowanie!");
        else stage.setTitle("Quizowanie - Zalogowany!");
        stage.show();
    }
    /**Metoda obsługująca komunikat błędu*/
    public static String errorChecking()//funkcja zwracajaca string z wiadomoscia do popupa o mozliwym bledzie
    {
        if(errors[0])
            return "Pole z trybem gry jest puste. Proszę wybrać tryb gry";
        else if(errors[1])
            return "Pole z poziomem trudności jest puste. Proszę wybrać poziom trudności";
        else if(errors[2])
            return "Pole z kategorią jest puste. Proszę wybrać kategorię";
        else return "Niech gra się zacznie";
    }
    /**Metoda obslugująca odjechanie myszki z przycisku powrotu*/
    public void onReturnButtonExited(MouseEvent mouseEvent) {
        returnImage.setStyle("-fx-opacity:0.3;");
    }
    /**Metoda obsługująca najechanie myszki na przycisk powrotu*/
    public void onReturnButtonEntered(MouseEvent mouseEvent) {
        returnImage.setStyle("-fx-opacity:1;");
    }
    @FXML
    public void onButtonExited(MouseEvent mouseEvent) {
        Button but = (Button) mouseEvent.getSource();
        if(ControllerSettings.backgroundColour == 1)
            but.setStyle(
                    "-fx-background-color: #696969;\n" +
                            "    -fx-background-insets: 0,1,2,3;\n" +
                            "    -fx-background-radius: 3,2,2,2;\n" +
                            "    -fx-text-fill: black;\n" +
                            "    -fx-font-size: 30px;");
    }
    @FXML
    public void onButtonEntered(MouseEvent mouseEvent) {
        Button but = (Button) mouseEvent.getSource();
        if(ControllerSettings.backgroundColour == 1)
            but.setStyle(
                    "-fx-background-color: #8a8a8a;\n" +
                            "    -fx-background-insets: 0,1,2,3;\n" +
                            "    -fx-background-radius: 3,2,2,2;\n" +
                            "    -fx-text-fill: black;\n" +
                            "    -fx-font-size: 30px;");

    }
}
