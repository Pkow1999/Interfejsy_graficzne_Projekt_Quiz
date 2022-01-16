package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**Klasa Zarządzająca metodami dodawania pytań do bazy danych*/
public class ControllerAddQuestion {
    /**Pole tekstowe zawierające pytanie które chcemy dodać*/
    @FXML
    private TextArea textArea;
    /**Pola tekstowe zawierające odpowiedzi do pytania*/
    @FXML
    private TextField Good,Bad1,Bad2,Bad3;//textfieldy zawierajacy odpowiedzi
    /**Menu Button zawierający kategorie danego pytania*/
    @FXML
    private MenuButton category;
    /**Kategorie dostępne do wybrania*/
    @FXML
    private MenuItem POLISH,HISTORY,ENGLISH,MATH;
    /**Image view obrazka wyjścia z okna*/
    @FXML
    private ImageView exitImage;

    /**Tablica służaca do sprawdzania błędów podczas dodawania pytania*/
    static private boolean[] errors = {true, true, true};//tablica posiadajaca errory mozliwe w dodawaniu pytania
    /**Scena do której się odwołujemy w chwili zmieniania okna*/
    private Stage stage;

    /**Metoda obsługująca wyjście z okna*/
    @FXML
    protected void onReturnButtonClick(ActionEvent event) throws IOException {
        //to jest czek ktory sprawdza czy ładuje view zalogowanego czy nie
        //jeden jest od razu na true bo jest to kategoria i zmieni sie jak ustawimy dowolna kategorie z mozliwych
        Parent home_page_parent;
        //to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej
        home_page_parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));//to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej

        //TODO
        Scene home_page_scene = new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        if(!ControllerMain.getLogOn())
            stage.setTitle("Quizowanie!");
        else stage.setTitle("Quizowanie - Zalogowany!");
        stage.show();
    }
    /**Metoda obsługująca zmiane kategorii*/
    @FXML
    protected void ChangeCategory(ActionEvent event) {
        //powinno dac rade to zrobic bardziej elegancko, ale nie mam teraz pojecia jak
        if(event.getTarget().equals(POLISH))
            category.setText(POLISH.getText());
        else if (event.getTarget().equals(HISTORY))
            category.setText(HISTORY.getText());
        else if (event.getTarget().equals(ENGLISH))
            category.setText(ENGLISH.getText());
        else if (event.getTarget().equals(MATH))
            category.setText(MATH.getText());
        errors[0] = false;
    }
    /**Metoda obsługująca wysłanie pytania do zatwierdzenia*/
    @FXML
    protected void onEnterQButtonClick(ActionEvent event) throws IOException {
        stage = new Stage();//robimy nowe okienko
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addQuestionPopUp.fxml"));
        Scene home_page_scene = new Scene(fxmlLoader.load());//jak robi sie nowa scene to tworzy sie nowe okno - mozna to uzyc przy historii
        stage.setScene(home_page_scene);
        stage.setResizable(false);
        stage.show();
    }
    /**Metoda sprawdzająca błędy w dodawaniu pytania*/
    public static String errorChecking()//funkcja zwracajaca string z wiadomoscia do popupa o mozliwym bledzie
    {
        if(errors[0])
            return "Pole z kategorią jest puste. Proszę wybrać kategorię";
        else if(errors[1])
            return "Pole z pytaniem jest puste. Proszę wprowadzić pytanie";
        else if(errors[2])
            return "Jedno lub kilka pól z odpowiedziami jest puste. Proszę uzupełnić odpowiedzi";
        else return "Pytanie zostało wysłane do zatwierdzenia";
    }
    /**Metoda która obsługuje textfieldy w chwili pusczenia klawisza aby zaaktualizować tablice błędów*/
    @FXML
    private void onReturnTextClick(KeyEvent keyEvent) {//metoda ktora sprawdza czy pola zostaly uzupelnione PO puszczeniu przycisku
        //bez tej funkcji moga pojawic sie opoznienia i bedzie trzeba naprzyklad kliknac w nowy textfield albo w okno aby dane zostaly zaakceptowane
        if(Good.getText().equals("") || Bad1.getText().equals("") || Bad2.getText().equals("") || Bad3.getText().equals(""))//sprawdzamy czy wszystkie odpowiedzi maja cos w sobie
        {
            errors[2] = true;
        }
        else errors[2] = false;
        if(textArea.getText().equals(""))//sprawdzamy czy w pytaniu nie ma pustego stringa
        {
            errors[1] = true;
        } else errors[1] = false;
    }

    /**Metoda obsługująca najechanie myszką na przycisk wyjścia z okienka*/
    public void onExitButtonEntered(MouseEvent mouseEvent) {
        exitImage.setStyle("-fx-opacity:1;");
    }
    /**Metoda obsługująca odjechanie myszką z przycisku wyjścia z okienka*/
    public void onExitButtonExited(MouseEvent mouseEvent) {
        exitImage.setStyle("-fx-opacity:0.3;");
    }
}
