package com.example.demoinclass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.CheckBox;

import java.net.URL;
import java.util.ResourceBundle;

public class FoodMenuChartViewController implements Initializable {

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private BarChart<String, Double> chartTopFoods;

    @FXML
    private NumberAxis numberAxis;
    @FXML
    private CheckBox chkShowSpicy;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DBUtility.configCridentials();

        chartTopFoods.getData().add(DBUtility.getTopFoodMenus());

        numberAxis.setLabel("Value");
        categoryAxis.setLabel("Name");
    }

    /*
    * Show or hide the spicy level data bars
    * */
    @FXML
    private void showSpicyLevel(ActionEvent event)
    {
        if (chkShowSpicy.isSelected())
        {
            DBUtility.configCridentials();
            chartTopFoods.getData().addAll(DBUtility.getTopFoodMenusWithSpicyLevel());
        }
        else
        {
            chartTopFoods.getData().remove(1);
        }
    }

}
