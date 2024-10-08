package com.example.demoinclass;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class CovidCaseChartViewController implements Initializable {
    @FXML
    private BarChart<String, Integer> barChartCovid;

    @FXML
    private RadioButton radioAge;

    @FXML
    private RadioButton radioGender;

    @FXML
    private RadioButton radioMonth;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup toggleGroup = new ToggleGroup();

        radioAge.setToggleGroup(toggleGroup);
        radioGender.setToggleGroup(toggleGroup);
        radioMonth.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) ->
                {
                    //Clear the view and add the selected data. 
                    barChartCovid.getData().clear();
                    if (radioAge.isSelected())
                    {
                        boolean b = barChartCovid.getData().addAll(DBUtility.getCasesByAge());
                    } else if (radioGender.isSelected()) {
                        boolean g =barChartCovid.getData().addAll(DBUtility.getCasesByGender());
                    } else if (radioMonth.isSelected()) {
                        boolean g =barChartCovid.getData().addAll(DBUtility.getCasesByMonth());
                    }
                }
        );

        //Set up the default view
        radioAge.setSelected(true);

    }
}
