package com.example.demoinclass;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FoodMenuTableViewController implements Initializable {
    @FXML
    private TableColumn<FoodMenu,Double> colCalorie;

    @FXML
    private TableColumn<FoodMenu, String> colName;

    @FXML
    private TableColumn<FoodMenu, Double> colPrice;

    @FXML
    private TableColumn<FoodMenu, Integer> colSpicy;

    @FXML
    private TableView<FoodMenu> foodMenuTableView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DBUtility.configCridentials();
        try {
            ArrayList<FoodMenu> foodMenus = DBUtility.getFoodMenus();
            foodMenuTableView.getItems().addAll(foodMenus);
            colName.setCellValueFactory(new PropertyValueFactory<>("foodName") );
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price") );
            colSpicy.setCellValueFactory(new PropertyValueFactory<>("spicyLevel") );
            colCalorie.setCellValueFactory(new PropertyValueFactory<>("calorie") );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /*
     * Switch the view
     *  */
    @FXML
    private void loadChartView(ActionEvent event) throws IOException {
        SceneManager.changeScenes(event,"foodMenuChartView.fxml");

    }
}
