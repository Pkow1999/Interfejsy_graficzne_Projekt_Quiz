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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Objects;

public class ControllerSettings {
    @FXML
    private Button button;
    @FXML
    private Pane background;
    @FXML
    private MenuItem Bright,Dark,Gradient;
    @FXML
    private ImageView exitImage;
    @FXML
    private MenuButton backgroundMenu;
    @FXML
    private Label numberCompletedQuiz,skonczonychquizow,motyw,login;
    private ArrayList<Label> labelArrayList = new ArrayList<>();
    @FXML
    private TextField loginField;
    private HistoryData data;
    public static int backgroundColour = 0;
    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        numberCompletedQuiz.setText(String.valueOf(ControllerSummaryAlternative.numbers));
        try {
            FileInputStream fis = new FileInputStream("DAT/"+ControllerMain.loginName+".dat");

            ObjectInputStream ois = new ObjectInputStream(fis);

            data = (HistoryData) ois.readObject();
            ois.close();
            numberCompletedQuiz.setStyle(String.valueOf(data.getCounter()));
        }catch (FileNotFoundException e)
        {
        }

        labelArrayList.add(skonczonychquizow);
        labelArrayList.add(motyw);
        labelArrayList.add(login);

        if(!ControllerMain.getLogOn())
        {
            backgroundMenu.setDisable(true);
            loginField.setDisable(true);
        }
        loginField.setText(ControllerMain.loginName);
        if(backgroundColour == 1)
        {
            backgroundMenu.setText(Dark.getText());
        }
        else if(backgroundColour == 2)
        {
            backgroundMenu.setText(Bright.getText());
        }
        else if(backgroundColour == 0)
        {
            backgroundMenu.setText(Gradient.getText());

        }
        changeColor(backgroundColour);
    }
    /**Metoda obsługująca najechanie myszki na przycisk powrotu*/
    public void onExitButtonExited(MouseEvent mouseEvent) {exitImage.setStyle("-fx-opacity:0.3;"); }
    /**Metoda obsługująca odjechanie myszki z przycisku powrotu*/
    public void onExitButtonEntered(MouseEvent mouseEvent) {exitImage.setStyle("-fx-opacity:1;");}
    /**Metoda obsługująca powrót do menu głównegos*/
    public void onReturnButtonClick(ActionEvent event) throws IOException {
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
    /**Metoda obsługująca zatwierdzenie zmian*/
    public void onEnterQButtonClick(ActionEvent event)
    {
        ControllerMain.loginName = loginField.getText();
        if(backgroundMenu.getText().equals(Gradient.getText()))
            backgroundColour = 0;
        else if(backgroundMenu.getText().equals(Dark.getText()))
            backgroundColour = 1;
        else if(backgroundMenu.getText().equals(Bright.getText()))
            backgroundColour = 3;
        changeColor(backgroundColour);

    }
    /**Metoda obsługujaca zmiane motywu*/
    public void ChangeBackground(ActionEvent event) {
        if (event.getTarget().equals(Gradient)) {
            backgroundMenu.setText(Gradient.getText());
        }
        else if(event.getTarget().equals(Dark)) {
            backgroundMenu.setText(Dark.getText());
        }
        else if (event.getTarget().equals(Bright)) {
            backgroundMenu.setText(Bright.getText());
        }
    }
    private void changeColor(int backgroundColour)
    {
        if(backgroundColour == 1) {
            background.setStyle("-fx-background-color: darkslategray");
            button.setStyle(
                    "-fx-background-color: #696969;\n" +
                            "    -fx-background-insets: 0,1,2,3;\n" +
                            "    -fx-background-radius: 3,2,2,2;\n" +
                            "    -fx-text-fill: beige;\n" +
                            "    -fx-font-size: 14px;");
            for (Label lejbel : labelArrayList)
            {
                lejbel.setStyle(lejbel.getStyle() + "-fx-text-fill: beige;");
            }
        }
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
