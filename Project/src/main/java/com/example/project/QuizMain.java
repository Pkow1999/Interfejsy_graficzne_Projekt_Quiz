package com.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**Klasa wyświetlająca główne okienko aplikacji*/
public class QuizMain extends Application {
    /**Metoda wyświetlająca główne okienko aplikacji*/
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizMain.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());//jak robi sie nowa scene to tworzy sie nowe okno - mozna to uzyc przy historii
        stage.setTitle("Quizowanie!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    /**Główna metoda włączająca aplikację*/
    public static void main(String[] args) {
        launch();
    }
}