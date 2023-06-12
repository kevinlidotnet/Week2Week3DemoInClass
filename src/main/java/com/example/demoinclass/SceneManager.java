package com.example.demoinclass;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    static public  void changeScenes(ActionEvent event, String fxmlName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlName));
        Scene scene = new Scene(fxmlLoader.load(), 520, 640);
        //Get the same stage instance
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Food Menu Sunday");
        stage.setScene(scene);
        stage.show();
    }



}
