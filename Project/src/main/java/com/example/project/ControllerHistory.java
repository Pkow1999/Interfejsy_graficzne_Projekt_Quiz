package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;

public class ControllerHistory {
    @FXML
    private SplitPane mainSplitPane;
    @FXML
    private SplitPane splitPane0;
    @FXML
    private SplitPane splitPane01;
    @FXML
    private SplitPane splitPane02;
    @FXML
    private SplitPane splitPane03;

    @FXML
    private Label timeLabel0;
    @FXML
    private Label percentageLabel0;
    @FXML
    private Label punctationLabel0;
    @FXML
    public Button Category0;
    @FXML
    void initialize()
    {
        mainSplitPane.getItems().remove(2,5);
        mainSplitPane.setPrefHeight(100);
    }

    public void ButtonExitClick(ActionEvent event) {
    }

    public void onMouseButtonEntered(MouseEvent mouseEvent) {
    }

    public void onMouseButtonExited(MouseEvent mouseEvent) {
    }
}
