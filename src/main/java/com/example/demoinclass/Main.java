package com.example.demoinclass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("foodMenuChartView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 540);
        stage.setTitle("Food Menu Sunday");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //DBUtility.creatFoodMenus();
        launch();
    }
}