package com.ibra.frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

/**
 * Controller for the main view
 */
public class MainController {

    @FXML
    private StackPane contentArea;

    @FXML
    private Label statusLabel;

    /**
     * Initialize the controller
     */
    @FXML
    private void initialize() {
        statusLabel.setText("Welcome to Hospital Information System");
    }

    /**
     * Opens the Patient view
     */
    @FXML
    private void openPatientView(ActionEvent event) {
        loadView("/view/PatientView.fxml");
        statusLabel.setText("Patient Management");
    }

    /**
     * Opens the Doctor view
     */
    @FXML
    private void openDoctorView(ActionEvent event) {
        showNotImplementedDialog("Doctor Management");
    }

    /**
     * Opens the Nurse view
     */
    @FXML
    private void openNurseView(ActionEvent event) {
        showNotImplementedDialog("Nurse Management");
    }

    /**
     * Opens the Department view
     */
    @FXML
    private void openDepartmentView(ActionEvent event) {
        showNotImplementedDialog("Department Management");
    }

    /**
     * Opens the Ward view
     */
    @FXML
    private void openWardView(ActionEvent event) {
        showNotImplementedDialog("Ward Management");
    }

    @FXML
    private void openHospitalizationView(ActionEvent event) {
        showNotImplementedDialog("Hospitalization Management");
    }


    @FXML
    private void showAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Hospital Information System");
        alert.setContentText("A comprehensive system for managing hospital resources and patients.\n\nVersion 1.0\n© 2025");
        alert.showAndWait();
    }

    @FXML
    private void handleExit(ActionEvent event) {
        System.exit(0);
    }

    private void loadView(String fxmlPath) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentArea.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Error Loading View", "Could not load view: " + fxmlPath, e.getMessage());
        }
    }

    private void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showNotImplementedDialog(String feature) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Not Implemented");
        alert.setHeaderText(feature + " is not implemented yet");
        alert.setContentText("This feature will be available in a future version.");
        alert.showAndWait();

        statusLabel.setText("Ready");
    }
}