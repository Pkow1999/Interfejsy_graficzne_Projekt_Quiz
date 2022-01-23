package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Objects;

public class ControllerHistory {
    @FXML
    private Button exit;
    @FXML
    private Label text;
    @FXML
    private Rectangle rectangle;
    @FXML
    private VBox background;
    @FXML
    private SplitPane mainSplitPane;

    @FXML
    private Label timeLabel0,timeLabel01,timeLabel02,timeLabel03;
    @FXML
    private Label percentageLabel0,percentageLabel01,percentageLabel02,percentageLabel03;
    @FXML
    private Label punctationLabel0,punctationLabel01,punctationLabel02,punctationLabel03;
    @FXML
    public Button Category0,Category01,Category02,Category03;

    private ArrayList<Button> buttonArrayList;
    private ArrayList <Label> timeLabelArrayList;
    private ArrayList <Label> percentageLabelArrayList;
    private ArrayList <Label> punctationLabelArrayList;

    private HistoryData data;
    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        try
        {

            FileInputStream fis = new FileInputStream("DAT/"+ControllerMain.loginName+".dat");

            ObjectInputStream ois = new ObjectInputStream(fis);

            data = (HistoryData) ois.readObject();
            ois.close();
            mainSplitPane.getItems().remove(data.getSize() + 1, 5);
            mainSplitPane.setPrefHeight(41 + (data.getSize() * 46));
            //latwiejszy dostep do buttonów
            buttonArrayList = new ArrayList<>();
            buttonArrayList.add(Category0);
            buttonArrayList.add(Category01);
            buttonArrayList.add(Category02);
            buttonArrayList.add(Category03);
            //to samo robimy dla czasu, punktacji i wyniku
            timeLabelArrayList = new ArrayList<>();
            timeLabelArrayList.add(timeLabel0);
            timeLabelArrayList.add(timeLabel01);
            timeLabelArrayList.add(timeLabel02);
            timeLabelArrayList.add(timeLabel03);

            percentageLabelArrayList = new ArrayList<>();
            percentageLabelArrayList.add(percentageLabel0);
            percentageLabelArrayList.add(percentageLabel01);
            percentageLabelArrayList.add(percentageLabel02);
            percentageLabelArrayList.add(percentageLabel03);

            punctationLabelArrayList = new ArrayList<>();
            punctationLabelArrayList.add(punctationLabel0);
            punctationLabelArrayList.add(punctationLabel01);
            punctationLabelArrayList.add(punctationLabel02);
            punctationLabelArrayList.add(punctationLabel03);
//          dzieki temu nie musimy wpisywac za kazdym razem np.: punctuationLabel03.setText("TEKST") bo mozemy wziac po prostu dany index ktory to zawiera
            //poza tym wielkosc tych setow bedzie dynamicznie dostosowywana na podstawie getSize() od daty, wiec nie bedziemy przechodzic przez nie istniejace komórki
            //mimo ze te komorki nie istnieja to dalej maja swoja referencje - przez FXMLa


            for(int i = 0; i < data.getSize(); i++)
            //basically w zaleznosci od ilosci naszych pytan w bazie, tyle bedziemy mogli edytowac komórek, nie użyte komórki i tak są usuwane więc nie musimy ich zerować
            {

                percentageLabelArrayList.get(i).setText(String.valueOf(data.daneDoPytan.get(i).get(2) * 10)+"%");
                if(data.daneDoPytan.get(i).get(5) == 1)//na 6stej pozycji mamy gamemode
                {
                    punctationLabelArrayList.get(i).setText(String.valueOf(data.daneDoPytan.get(i).get(3)));
                    timeLabelArrayList.get(i).setText(String.valueOf(data.daneDoPytan.get(i).get(4))+" sek.");
                }
                else {
                    punctationLabelArrayList.get(i).setText("Tryb nauki");
                    timeLabelArrayList.get(i).setText("Tryb nauki");
                }

                if(data.daneDoPytan.get(i).get(0) == 1)
                {
                    buttonArrayList.get(i).setText("Język Polski");
                }
                else if(data.daneDoPytan.get(i).get(0) == 2)
                {
                    buttonArrayList.get(i).setText("Historia");
                }
                else if(data.daneDoPytan.get(i).get(0)  == 4)
                {
                    buttonArrayList.get(i).setText("Matematyka");
                }
                else if(data.daneDoPytan.get(i).get(0) == 3)
                {
                    buttonArrayList.get(i).setText("Język Angielski");
                }


                if(data.daneDoPytan.get(i).get(2) < 5)
                {
                    buttonArrayList.get(i).setStyle("-fx-background-color:Tomato;-fx-text-fill:white");
                    timeLabelArrayList.get(i).setStyle("-fx-background-color:FireBrick;-fx-text-fill:white");
                    percentageLabelArrayList.get(i).setStyle("-fx-background-color:FireBrick;-fx-text-fill:white");
                    punctationLabelArrayList.get(i).setStyle("-fx-background-color:FireBrick;-fx-text-fill:white");
                }
            }
        }
        catch (FileNotFoundException e)//ten wyjątek jest po to co ma sie dziac jesli nasza historia nie zostala jeszcze stworzona
        {
            mainSplitPane.getItems().remove(1, 5);
            mainSplitPane.setPrefHeight(41);
        }

        if(ControllerSettings.backgroundColour == 1)
        {
            background.setStyle("-fx-background-color: darkslategray");
            rectangle.setStyle(rectangle.getStyle() + "-fx-fill: #556b2f");
            text.setStyle(text.getStyle() + "-fx-text-fill: BLACK");
            exit.setStyle(
                    "-fx-background-color: #696969;\n" +
                            "    -fx-background-insets: 0,1,2,3;\n" +
                            "    -fx-background-radius: 3,2,2,2;\n" +
                            "    -fx-text-fill: beige;\n" +
                            "    -fx-font-size: 14px;");
        }

    }

    public void ButtonExitClick(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));//to sie rozwala - bo nakladamy kolejna wartswe zamiast wrocic do poprzedniej

        //TODO
        Scene home_page_scene = new Scene(home_page_parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        if(!ControllerMain.getLogOn())
            stage.setTitle("Quizowanie!");
        else stage.setTitle("Quizowanie - Zalogowany!");
        stage.show();
    }

    public void onMouseButtonEntered(MouseEvent mouseEvent) {
    }

    public void onMouseButtonExited(MouseEvent mouseEvent) {
    }


    public void onButtonExited(MouseEvent mouseEvent) {
        Button but = (Button) mouseEvent.getSource();
        if(ControllerSettings.backgroundColour == 1)
            but.setStyle(
                    "-fx-background-color: #696969;\n" +
                            "    -fx-background-insets: 0,1,2,3;\n" +
                            "    -fx-background-radius: 3,2,2,2;\n" +
                            "    -fx-text-fill: beige;\n" +
                            "    -fx-font-size: 14px;");
    }

    public void onButtonEntered(MouseEvent mouseEvent) {
        Button but = (Button) mouseEvent.getSource();
        if(ControllerSettings.backgroundColour == 1)
            but.setStyle(
                    "-fx-background-color: #8a8a8a;\n" +
                            "    -fx-background-insets: 0,1,2,3;\n" +
                            "    -fx-background-radius: 3,2,2,2;\n" +
                            "    -fx-text-fill: beige;\n" +
                            "    -fx-font-size: 14px;");

    }
}
