package com.example.demoinclass;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class CovidCaseChartViewController implements Initializable {
    @FXML
    private NumberAxis barChartCovid;

    @FXML
    private RadioButton radioAge;

    @FXML
    private RadioButton radioGender;

    @FXML
    private RadioButton radioMonth;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup toggleGroup = new ToggleGroup();

        radioAge.setToggleGroup(toggleGroup);
        radioGender.setToggleGroup(toggleGroup);
        radioMonth.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> System.out.println(newVal + " was selected"));

    }
}
