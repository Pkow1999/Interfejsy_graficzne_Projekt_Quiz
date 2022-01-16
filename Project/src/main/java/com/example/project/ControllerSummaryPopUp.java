package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;


/**Klasa obsługująca wyświetlane szczegółów danego pytania*/
public class ControllerSummaryPopUp {
    @FXML
    private Label QuestionText,Anwser1,Anwser2,Anwser3,Anwser4;
    /**Zmienna trzymająca wszystkie odpowiedzi*/
    private ArrayList<Label> AnswersLabel = new ArrayList<>();
    /**Metoda inicjalizująca*/
    public void initialize()//initialize dziala PO konstruktorze i PO FXMLu ALE PRZED showem
    {
        AnswersLabel.add(Anwser1);
        AnswersLabel.add(Anwser2);
        AnswersLabel.add(Anwser3);
        AnswersLabel.add(Anwser4);
        QuestionText.setText(ControllerSummaryAlternative.questionContent(ControllerSummaryAlternative.index));
        for(int i = 0;i < AnswersLabel.size(); i++)
        {
            AnswersLabel.get(i).setText(ControllerSummaryAlternative.questionsAnwser(ControllerSummaryAlternative.index,i+1));
        }
        if(Questions.getAnswer(ControllerSummaryAlternative.index))
            Anwser1.setStyle(Anwser1.getStyle() + "-fx-text-fill:green; -fx-underline:true;");
        else {
            Anwser1.setStyle(Anwser1.getStyle() + "-fx-text-fill:forestgreen; -fx-underline:true;");
            for(Label lejbel: AnswersLabel)
            {
                if(lejbel.getText().equals(Questions.odpowiedzi.get(ControllerSummaryAlternative.index)))
                {
                    lejbel.setStyle("-fx-text-fill:red;");
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
