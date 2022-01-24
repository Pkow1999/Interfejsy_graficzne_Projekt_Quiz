package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**Klasa obsługująca główne okienko aplikacji*/
public class ControllerMain {
    /**Zmienna która przechowywuje stan zalogowywania aplikacji*/
    private static boolean logOn = false;//zmienna ktora przechowywuje czy jestes zalogowany


    @FXML
    private Rectangle rectangle;
    @FXML
    private VBox background;
    @FXML
    private Button button0,button1,button2,button3;
    private ArrayList<Button> ButtonList = new ArrayList<>();

    /**Zmienna zwracająca stan zalogowywania*/
    public static boolean getLogOn(){return logOn;}

    public static String loginName;

    /**Zmienna która przechowywuje Imageview obrazka logowania/wylogowywania*/
    @FXML
    private ImageView LoginImage,SettingImage;
    @FXML
    private TextField login;
    @FXML
    public Label loginLabel;
    /**Napis zawierający informację co przycisk w prawym górnym rogu robi*/
    @FXML
    private Text LoginText;
    /**Napis przechowywujący napis witający użytkownika*/
    @FXML
    private Label welcomeText;

    private Parent home_page_parent;
    private Scene home_page_scene;
    private Stage stage;
    /**Konstruktor*/

    public ControllerMain()
    {
        LoginText = new Text();
    }
    /**Metoda inicjalizująca*/
    @FXML
    public void initialize()
    {
        ButtonList.add(button0);
        ButtonList.add(button1);
        ButtonList.add(button2);
        ButtonList.add(button3);

        if(getLogOn())
        {
            LoginText.setText("Wyloguj:");
            LoginImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logout.png"))));
            LoginImage.setFitHeight(50);
            LoginImage.setFitWidth(50);
        }
        else
        {
            LoginText.setText("Zaloguj:");
        }

        //0-gradient 1-ciemny
        if(ControllerSettings.backgroundColour == 1)
        {
            background.setStyle("-fx-background-color: darkslategray");
            rectangle.setStyle(rectangle.getStyle() + "-fx-fill: #556b2f");
            welcomeText.setStyle(welcomeText.getStyle() + "-fx-text-fill: BLACK");
            for(Button przycisk : ButtonList)
            {
                przycisk.setStyle(
                        "-fx-background-color: #696969;\n" +
                        "    -fx-background-insets: 0,1,2,3;\n" +
                        "    -fx-background-radius: 3,2,2,2;\n" +
                        "    -fx-text-fill: Black;\n" +
                        "    -fx-font-size: 14px;");
            }
        }
    }
    /**Metoda obsługująca kliknięcie w zalogowywanie/wylogowywanie z aplikacji*/
    @FXML
    protected void onLoginClick(ActionEvent event) throws IOException {
        //Zamykamy stare okienko
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        if(!getLogOn()){
            stage = new Stage();//robimy nowe okienko z ekranem logowania
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Scene home_page_scene = new Scene(fxmlLoader.load());//jak robi sie nowa scene to tworzy sie nowe okno - mozna to uzyc przy historii
            stage.setScene(home_page_scene);
            stage.setResizable(false);
            stage.setTitle("Zaloguj się!");
        }
        else {
            logOn = false;
            loginName = null;
            home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
            home_page_scene =  new Scene(home_page_parent);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(home_page_scene);
            stage.setTitle("Quizowanie!");
        }
        stage.show();
    }
    /**Metoda obsługująca okienko zalogowania*/
    @FXML
    protected void onLoginClickedClick(ActionEvent event) throws IOException {
        if (!login.getText().equals(""))
        {
            logOn = true;
            loginName = login.getText();
            //zamykamy okienko z logowaniem
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.close();
            //otwieramy okienko main po zalogowaniu
            home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
            home_page_scene =  new Scene(home_page_parent);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(home_page_scene);
            stage.setTitle("Quizowanie - Zalogowany!");
            stage.show();
        }
        else {
            loginLabel.setStyle(loginLabel.getStyle() + "-fx-text-fill:red;");
            loginLabel.setText("Pole z loginem nie może być puste");
        }
    }

    /**Metoda obsługująca wyjście z okienka zalogowania*/
    @FXML
    protected void onBackClicked(ActionEvent event) throws IOException {
        //zamykamy okienko z logowaniem
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
        //otwieramy okienko main po zalogowaniu
        home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        home_page_scene =  new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        stage.setTitle("Quizowanie!");
        stage.show();
    }
    /**Metoda obsługująca chęć zagrania w quiz*/
    @FXML
    protected void onPlayButtonClick(ActionEvent event) throws IOException {
        home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("selectGameMode.fxml")));
        home_page_scene =  new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        stage.setTitle("Quizowanie! - Tryby gry");
        stage.show();
    }
    /**Metoda obsługujaca chęć dodania pytania*/
    @FXML
    protected void onAddQuestionButtonClick(ActionEvent event) throws IOException {
        if(!getLogOn()){
            stage = new Stage();//robimy nowe okienko
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("notLoggedInPopUp.fxml"));
            Scene home_page_scene = new Scene(fxmlLoader.load());//jak robi sie nowa scene to tworzy sie nowe okno - mozna to uzyc przy historii
            stage.setScene(home_page_scene);
            stage.setResizable(false);
        }
        else {
            home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addQuestion-view.fxml")));
            home_page_scene =  new Scene(home_page_parent);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(home_page_scene);
            stage.setTitle("Quizowanie! - Dodaj pytanie!");
        }
        stage.show();
    }
    /**Metoda obsługująca chęć sprawdzenia historii*/
    @FXML
    protected void onCheckHistoryClick(ActionEvent event) throws IOException {
        if(!getLogOn()){
            stage = new Stage();//robimy nowe okienko
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("notLoggedInPopUp.fxml"));
            Scene home_page_scene = new Scene(fxmlLoader.load());//jak robi sie nowa scene to tworzy sie nowe okno - mozna to uzyc przy historii
            stage.setScene(home_page_scene);
            stage.setResizable(false);
            stage.show();
        }
        else
        {
            home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("history-view.fxml")));
            home_page_scene =  new Scene(home_page_parent);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(home_page_scene);
            stage.setTitle("Quizowanie! - Tryby gry");
            stage.show();
        }
    }
    /**Metoda obsługująca chęć wyjścia z aplikacji*/
    @FXML
    protected void onExitClick()
    {
        Platform.exit();
    }
    /**Metoda obsługująca wyjście z okienka informującym o potrzebie zalogowania się*/
    public void onExitPopUpButtonClick(ActionEvent event) {
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    /**Metoda obsługująca najechanie myszką na przycisk zalogowywania/wylogowywania się*/
    public void onLoginButtonEntered(MouseEvent mouseEvent) {
        LoginImage.setStyle("-fx-opacity:1;");
    }
    /**Metoda obsługująca odjechanie myszką z przycisku zalogowywania/wylogowywania się*/
    public void onLoginButtonExited(MouseEvent mouseEvent) {
        LoginImage.setStyle("-fx-opacity:0.3;");
    }

    /**Metoda obsługująca chęc wejścia do ustawień */
    public void onSettingsClick(ActionEvent event) throws IOException {
        home_page_parent= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("setting-view.fxml")));
        home_page_scene =  new Scene(home_page_parent);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(home_page_scene);
        stage.setTitle("Quizowanie! - Opcje");
        stage.show();
    }

    /**Metoda obsługująca najechanie myszką na przycisk zalogowywania/wylogowywania się*/
    public void onSettingsButtonEntered(MouseEvent mouseEvent) {SettingImage.setStyle("-fx-opacity:1;");}

    /**Metoda obsługująca odjechanie myszką z przycisku zalogowywania/wylogowywania się*/
    public void onSettingsButtonExited(MouseEvent mouseEvent) {SettingImage.setStyle("-fx-opacity:0.3;");}

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
