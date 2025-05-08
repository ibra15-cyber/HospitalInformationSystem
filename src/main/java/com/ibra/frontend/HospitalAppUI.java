package com.ibra.frontend;

import com.ibra.dbConnection.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;


public class HospitalAppUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main view
//        FXMLLoader loader = new FXMLLoader(new File("/development/java/Amalitech/HospitalInformationSystem/src/main/java/com/ibra/frontend/view/Main.fxml").toURI().toURL());
//
//        Parent root = loader.load();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));

        // Set the scene
        Scene scene = new Scene(root, 800, 600);

        // Configure the stage
        primaryStage.setTitle("Hospital Information System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        // Cleanup resources when application closes
        try {
            DBConnection.closeConnection();
        } catch (Exception e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
}