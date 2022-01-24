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

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**Klasa obsługująca ekran podsumowania*/
public class ControllerSummaryAlternative {
    public Label label;
    public VBox background;
    public Rectangle rec;
    public Button exit;
    public SplitPane secondsplit;
    public SplitPane mainsplit;
    @FXML
    private Button question1,question2,question3,question4,question5,question6,question7,question8,question9,question10;
    @FXML
    private Label percentageLabel;
    @FXML
    private Label punctationLabel;
    @FXML
    private Label timeLabel;

    private int counter = 0;
    static public int numbers = 0;
    /**Zmienna przechowywująca indeks pytania*/
    public static int index;
    /**Metoda inicjalizująca*/
    @FXML
    public void initialize() {


        if(ControllerSettings.backgroundColour == 1)
        {
            ArrayList<Label> labelArrayList = new ArrayList<>();
            labelArrayList.add(percentageLabel);
            labelArrayList.add(punctationLabel);
            labelArrayList.add(timeLabel);
            background.setStyle("-fx-background-color: darkslategray");
            rec.setStyle(rec.getStyle() + "-fx-fill: #556b2f");
            exit.setStyle(
                    "-fx-background-color: #696969;\n" +
                            "    -fx-background-insets: 0,1,2,3;\n" +
                            "    -fx-background-radius: 3,2,2,2;\n" +
                            "    -fx-text-fill: Black;\n" +
                            "    -fx-font-size: 14px;");
            label.setStyle("-fx-text-fill: black");
            for(Label lab : labelArrayList)
            {
                lab.setStyle("-fx-text-fill: black; -fx-background-color: dimgray");
            }
            mainsplit.setStyle("-fx-background-color: darkslategray");
            secondsplit.setStyle("-fx-background-color: darkslategray");
        }
        numbers++;
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
        if(Questions.gamemode)
        {
            percentageLabel.setText(counter * 10 +"%");
            timeLabel.setText(Questions.getTime()+" sek.");
            punctationLabel.setText(Questions.getPunctation()+" pkt.");
        }
        else
        {
            percentageLabel.setText(counter * 10 +"%");
            timeLabel.setText("Tryb Nauki");
            punctationLabel.setText("Tryb Nauki");
        }
    }
    /**Metoda obsługująca wyjście z ekranu podsumowania i powrotu do ekranu głównego*/
    public void ButtonExitClick(ActionEvent event)  {
            try {
                if(ControllerMain.getLogOn())
                    serializeData();

                Parent home_page_parent;
                home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));

                Scene home_page_scene = new Scene(home_page_parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(home_page_scene);
                if(!ControllerMain.getLogOn())
                    stage.setTitle("Quizowanie!");
                else stage.setTitle("Quizowanie - Zalogowany!");
                stage.show();
            }
            catch (FileNotFoundException e)
            {
                label.setText(e.toString());
                timeLabel.setText(new File(".").getAbsolutePath());
            }
            catch (IOException e)
            {
                label.setText(e.toString());
            }
            catch (ClassNotFoundException e)
            {
                label.setText(e.toString());
            }
    }
    /**Metoda obsługująca wejście w szczegóły dotyczące danego pytania*/
    public void ButtonQuestionClick(ActionEvent event) throws IOException{
        index = Integer.parseInt(((Button)event.getSource()).getText()) - 1;

        Stage stage = new Stage();//robimy nowe okienko z ekranem logowania
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popUp.fxml"));
        Scene home_page_scene = new Scene(fxmlLoader.load());//jak robi sie nowa scene to tworzy sie nowe okno - mozna to uzyc przy historii
        stage.setScene(home_page_scene);
        stage.setResizable(false);
        stage.setTitle("Pytanie nr: " +((Button)event.getSource()).getText());
        stage.show();
    }
    /**Metoda obsługująca zwracanie zawartości pytania o danym indeksie o danej kategorii*/
    public static String questionContent(int index){
        if(ControllerSelectGameMode.kategoria == 1 && ControllerSelectGameMode.intLevelOfDifficulty == 0)
        {
            return Questions.polskiPytaniaEasy.get(index)[0];
        }
        else if(ControllerSelectGameMode.kategoria == 1 && ControllerSelectGameMode.intLevelOfDifficulty == 1)
        {
            return Questions.polskiPytaniaMed.get(index)[0];
        }
        else if(ControllerSelectGameMode.kategoria == 1 && ControllerSelectGameMode.intLevelOfDifficulty == 2)
        {
            return Questions.polskiPytaniaHard.get(index)[0];
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
    /**Metoda obsługująca zwracanie danej odpowiedzi z danego pytania*/
    public static String questionsAnwser(int index,int nr)
    {
        if(ControllerSelectGameMode.kategoria == 1 && ControllerSelectGameMode.intLevelOfDifficulty == 0)
        {
            return Questions.polskiPytaniaEasy.get(index)[nr];
        }
        else if(ControllerSelectGameMode.kategoria == 1 && ControllerSelectGameMode.intLevelOfDifficulty == 1)
        {
            return Questions.polskiPytaniaMed.get(index)[nr];
        }
        else if(ControllerSelectGameMode.kategoria == 1 && ControllerSelectGameMode.intLevelOfDifficulty == 2)
        {
            return Questions.polskiPytaniaHard.get(index)[nr];
        }
        else if(ControllerSelectGameMode.kategoria == 2)
        {
            return Questions.historiaPytania.get(index)[nr];
        }
        else if(ControllerSelectGameMode.kategoria == 3)
        {
            return Questions.angielskiPytania.get(index)[nr];
        }
        else
        {
            return Questions.matematykaPytania.get(index)[nr];
        }
    }
    /**Metoda obsługująca zmiane koloru pytania po najechaniu na nie myszką*/
    public void onMouseButtonEntered(MouseEvent mouseEvent) {
        Button source = (Button) mouseEvent.getSource();
        if(source.getStyle().equals("-fx-background-color: red;"))
            source.setStyle("-fx-background-color: LightCoral;");
        else source.setStyle("-fx-background-color: lightgreen;");
    }
    /**Metoda obsługująca zmiane koloru pytania po odjechaniu od niej myszką*/
    public void onMouseButtonExited(MouseEvent mouseEvent) {
        Button source = (Button) mouseEvent.getSource();
        if(source.getStyle().equals("-fx-background-color: LightCoral;"))
            source.setStyle("-fx-background-color: red;");
        else source.setStyle("-fx-background-color: green;");
    }

    private void serializeData() throws IOException, ClassNotFoundException, FileNotFoundException {
        //login|arrayZOdpowiedziami|kategoria|poziomTrudnosci|procentOdpowiedzi|punktacja|czas|gamemode
        ArrayList<Integer> dane = new ArrayList<>();
        dane.add(ControllerSelectGameMode.kategoria);
        dane.add(ControllerSelectGameMode.intLevelOfDifficulty);
        dane.add(counter);
        dane.add(Questions.getPunctation());
        dane.add(Questions.getTime());
        if(Questions.gamemode)
            dane.add(1);
        else
            dane.add(0);

        File theDir = new File("DAT");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        File playerData = new File("DAT/"+ControllerMain.loginName+".dat");
        
        if(playerData.isFile())
        {
            label.setText("ISTNIEJE");

            FileInputStream fis = new FileInputStream(playerData);

            ObjectInputStream ois = new ObjectInputStream(fis);

            HistoryData data = (HistoryData) ois.readObject();
            ois.close();
            data.saveData(Questions.odpowiedzi,dane);
            FileOutputStream fos = new FileOutputStream(playerData);

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(data);
            oos.close();
            label.setText("KONIEC ISNTIENIA");
        }
        else
        {
            label.setText("Nie istnieje - tworze nowy");
            FileOutputStream fos = new FileOutputStream(playerData);

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            HistoryData data = new HistoryData(ControllerMain.loginName, Questions.odpowiedzi, dane);

            oos.writeObject(data);
            oos.close();
            label.setText("KONIEC NIE ISNTIENIA");
        }

    }
    public void onButtonExited(MouseEvent mouseEvent) {
        Button but = (Button) mouseEvent.getSource();
        if(ControllerSettings.backgroundColour == 1)
            but.setStyle(
                    "-fx-background-color: #696969;\n" +
                            "    -fx-background-insets: 0,1,2,3;\n" +
                            "    -fx-background-radius: 3,2,2,2;\n" +
                            "    -fx-text-fill: black;\n" +
                            "    -fx-font-size: 14px;");
    }

    public void onButtonEntered(MouseEvent mouseEvent) {
        Button but = (Button) mouseEvent.getSource();
        if(ControllerSettings.backgroundColour == 1)
            but.setStyle(
                    "-fx-background-color: #8a8a8a;\n" +
                            "    -fx-background-insets: 0,1,2,3;\n" +
                            "    -fx-background-radius: 3,2,2,2;\n" +
                            "    -fx-text-fill: black;\n" +
                            "    -fx-font-size: 14px;");

    }

}
