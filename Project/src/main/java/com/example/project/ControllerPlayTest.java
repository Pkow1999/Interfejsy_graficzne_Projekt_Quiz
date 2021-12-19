package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerPlayTest {
    @FXML
    private Label questionText;

    @FXML
    protected void onHello1ButtonClick() {questionText.setText("1");}
    @FXML
    protected void onHello2ButtonClick() {questionText.setText("2");}
    @FXML
    protected void onHello3ButtonClick() {questionText.setText("3");}
    @FXML
    protected void onHello4ButtonClick() {questionText.setText("4");}
}
