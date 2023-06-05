package com.example.demoinclass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("createFoodMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 540);
        stage.setTitle("Create Food Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DBUtility.creatFoodMenus();
        //launch();
    }
}