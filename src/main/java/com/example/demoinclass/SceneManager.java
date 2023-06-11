package com.example.demoinclass;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    /*
    * Change to the argument value scene
    * */
    static public void changeScenes(ActionEvent event,String fxmlName) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlName));
        Scene scene = new Scene(fxmlLoader.load(), 620, 540);
        //Derive the stage instance
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Food Menu Sunday");
        stage.setScene(scene);
        stage.show();
    }
}
