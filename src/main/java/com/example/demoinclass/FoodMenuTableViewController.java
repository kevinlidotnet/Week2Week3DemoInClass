package com.example.demoinclass;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FoodMenuTableViewController implements Initializable {
    @FXML
    private TableColumn<FoodMenu,Integer> colCalorie;

    @FXML
    private TableColumn<FoodMenu, String> colName;

    @FXML
    private TableColumn<FoodMenu, Double> colPrice;

    @FXML
    private TableColumn<FoodMenu,Integer> colSpicyLevel;

    @FXML
    private TableView<FoodMenu> tableViewFoodMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSpicyLevel.setCellValueFactory(new PropertyValueFactory<>("spicyLevel"));
        colCalorie.setCellValueFactory(new PropertyValueFactory<>("calorie"));
        DBUtility.getCredentials();
        ArrayList<FoodMenu> foodMenus = DBUtility.getFoodMenus();
        tableViewFoodMenu.getItems().addAll(foodMenus);

    }
}
