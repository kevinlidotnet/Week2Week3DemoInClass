package com.example.demoinclass;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreateFoodMenu implements Initializable {

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<Integer> combSpicyLevel;

    @FXML
    private TextField txtFoodName;

    @FXML
    private TextField txtPrice;

    @FXML
    private Label txtResult;

    /*
    * This method will create the object with the values from the controls
    * */
    public void buttonSaveClicked() throws SQLException {
        FoodMenu foodMenu = new FoodMenu(txtFoodName.getText(),Double.parseDouble( txtPrice.getText()),combSpicyLevel.getValue());
        DBUtility.insertFoodMenu(foodMenu);
        txtResult.setText(foodMenu.toString());
    }

    /*
    * Initialize the dropdown list for spicy level
    * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        combSpicyLevel.getItems().addAll(Arrays.asList(0,1,2,3));
        //demoCom.getItems().addAll(Arrays.asList("None","Mild","Hot","Extreme"));
        //add the changelistener to allow only digits.
        //get the text field from the combo box.
        TextField comboBoxField = combSpicyLevel.getEditor();
        //comboBoxField.textProperty().addListener(new SpicyLevelChangeListener());
        //Create anonymous inner class
//        comboBoxField.textProperty().addListener(new ChangeListener<String>() {
//                                                     @Override
//                                                     public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                                                         //Check the new number is a digit
//                                                         try{
//                                                             Integer.parseInt(newValue.toString());
//                                                         }
//                                                         catch (Exception e)
//                                                         {
//                                                             System.out.println(String.format("%s is not a number!",newValue));
//                                                             txtResult.setText(String.format("%s is not a number!",newValue));
//                                                         }
//                                                     }
//                                                 }
//
//        );
        comboBoxField.textProperty().addListener(((observable, oldValue, newValue) ->
        {
            //Check the new number is a digit
             try{
                 Integer.parseInt(newValue.toString());
             }
             catch (Exception e)
             {
                 combSpicyLevel.getEditor().setText(oldValue.toString());
                 System.out.println(String.format("%s is not a number!",newValue));
                 txtResult.setText(String.format("%s is not a number!",newValue));
             }
        }));
    }
}
