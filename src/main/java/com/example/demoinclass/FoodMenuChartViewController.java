package com.example.demoinclass;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class FoodMenuChartViewController implements Initializable {

    @FXML
    private BarChart<String, Double> barChartFoodMenu;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;

    private XYChart.Series<String,Double> topFoodMenus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DBUtility.configCridentials();

        barChartFoodMenu.getData().addAll(DBUtility.getTopFoodMenus());
        numberAxis.setLabel("Value");
        categoryAxis.setLabel("Name");


        barChartFoodMenu.getData().addAll(DBUtility.getTopFoodMenusWithSpicyLevel());


    }
}
