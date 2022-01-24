package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;


/**Klasa obsługująca wyświetlane szczegółów danego pytania*/
public class ControllerSummaryPopUp {
    public Pane background;
    @FXML
    private Label QuestionText,Anwser1,Anwser2,Anwser3,Anwser4;
    /**Zmienna trzymająca wszystkie odpowiedzi*/
    private ArrayList<Label> AnswersLabel = new ArrayList<>();
    /**Metoda inicjalizująca*/
    public void initialize()//initialize dziala PO konstruktorze i PO FXMLu ALE PRZED showem
    {

        //tablica z naszymi labelami do latwiejszego przechodzenia przez nie
        AnswersLabel.add(Anwser1);
        AnswersLabel.add(Anwser2);
        AnswersLabel.add(Anwser3);
        AnswersLabel.add(Anwser4);
        //ustawiamy sobie nasze pytanie o wartosc ktora zapisalismy z funkcji questionContent z controllerSummaryAlternative
        QuestionText.setText(ControllerSummaryAlternative.questionContent(ControllerSummaryAlternative.index));


        if(ControllerSettings.backgroundColour == 1)
        {
            background.setStyle("-fx-background-color: dimgray");
            for(Label lejbel : AnswersLabel)
                lejbel.setStyle("-fx-text-fill: black;");
            QuestionText.setStyle(QuestionText.getStyle() + "-fx-text-fill: black");
        }


        //kazdemu lejbelowi dajemy odpowiednia odpowiedz z bazy pytan
        for(int i = 0;i < AnswersLabel.size(); i++)
        {
            AnswersLabel.get(i).setText(ControllerSummaryAlternative.questionsAnwser(ControllerSummaryAlternative.index,i+1));
        }
        //jesli nasza odpowiedz w danym indeksie byla prawidlowa to zaznaczamy ja kolorem zielonym i podkreslamy
        if(Questions.getAnswer(ControllerSummaryAlternative.index)){
            Anwser1.setStyle(Anwser1.getStyle() + "-fx-text-fill:green; -fx-underline:true;");
            if(ControllerSettings.backgroundColour == 1)
            {
                Anwser1.setStyle(Anwser1.getStyle() + "-fx-text-fill:limegreen; -fx-underline:true;");
            }
        }
        else {//jak nie to zaznaczamy poprawna kolorem zielonym bez podkreslenia
            Anwser1.setStyle(Anwser1.getStyle() + "-fx-text-fill:forestgreen; -fx-underline:false;");
            if(ControllerSettings.backgroundColour == 1)
            {
                Anwser1.setStyle(Anwser1.getStyle() + "-fx-text-fill:lightgreen; -fx-underline:false;");
            }
            for(Label lejbel: AnswersLabel)//i szukamy naszej odpowiedzi, zaznaczamy ja kolorem czerwonym i podkreslamy
            {
                if(lejbel.getText().equals(Questions.odpowiedzi.get(ControllerSummaryAlternative.index)))
                {
                    lejbel.setStyle("-fx-text-fill:red;-fx-underline:true;");
                    if(ControllerSettings.backgroundColour == 1)
                        lejbel.setStyle("-fx-text-fill:lightcoral;-fx-underline:true;");
                    break;
                }
            }
        }
    }
    /**Metoda obsługująca zamknięcie okna po naciśnięciu przycisku*/
    @FXML
    public void onExitButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
