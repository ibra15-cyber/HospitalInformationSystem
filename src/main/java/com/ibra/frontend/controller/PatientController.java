package com.ibra.frontend.controller;

import com.ibra.entity.Patient;
import com.ibra.service.PatientService;
import com.ibra.service.PatientServiceImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Controller for the Patient view
 */
public class PatientController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Patient> patientTable;

    @FXML
    private TableColumn<Patient, Integer> idColumn;

    @FXML
    private TableColumn<Patient, String> lastNameColumn;

    @FXML
    private TableColumn<Patient, String> firstNameColumn;

    @FXML
    private TableColumn<Patient, String> addressColumn;

    @FXML
    private TableColumn<Patient, String> phoneColumn;

    @FXML
    private TextField idField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextArea addressField;

    @FXML
    private TextField phoneField;

    @FXML
    private TableView hospitalizationsTable;

    @FXML
    private Label statusLabel;

    private PatientService patientService;
    private ObservableList<Patient> patientList;
    private boolean isEditMode = false;

    /**
     * Initialize the controller
     */
    @FXML
    private void initialize() {
        patientService = new PatientServiceImp();

        // Configure table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        // Load patient data
        loadPatientData();

        // Set up table selection listener
        patientTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPatientDetails(newValue)
        );

        // Disable detail fields initially
        setFieldsEditable(false);
        clearFields();
    }

    /**
     * Loads patient data from the database
     */
    private void loadPatientData() {
        try {
            List<Patient> patients = patientService.getAllPatients();
            patientList = FXCollections.observableArrayList(patients);
            patientTable.setItems(patientList);
            statusLabel.setText("Loaded " + patients.size() + " patients");
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Error Loading Data", "Failed to load patient data", e.getMessage());
            statusLabel.setText("Error loading data");
        }
    }

    private void showPatientDetails(Patient patient) {
        if (patient != null) {
            // Populate fields with patient data
            idField.setText(String.valueOf(patient.getPatientId()));
            lastNameField.setText(patient.getSurname());
            firstNameField.setText(patient.getFirstName());
            addressField.setText(patient.getAddress());
            phoneField.setText(patient.getTelephone());

            // TODO: Load hospitalization data for this patient

            setFieldsEditable(false);
            isEditMode = false;
        } else {
            clearFields();
        }
    }

    /**
     * Sets whether form fields are editable
     *
     * @param editable Whether fields should be editable
     */
    private void setFieldsEditable(boolean editable) {
        lastNameField.setEditable(editable);
        firstNameField.setEditable(editable);
        addressField.setEditable(editable);
        phoneField.setEditable(editable);

        // Visual feedback for edit mode
        String style = editable ? "-fx-background-color: #f8f8e8;" : "-fx-background-color: #f0f0f0;";
        lastNameField.setStyle(style);
        firstNameField.setStyle(style);
        addressField.setStyle(style);
        phoneField.setStyle(style);
    }

    /**
     * Clears all form fields
     */
    private void clearFields() {
        idField.clear();
        lastNameField.clear();
        firstNameField.clear();
        addressField.clear();
        phoneField.clear();
    }

    /**
     * Handles adding a new patient
     */
    @FXML
    private void handleAddNew(ActionEvent event) {
        clearFields();
        setFieldsEditable(true);
        isEditMode = false;

        lastNameField.requestFocus();
        statusLabel.setText("Adding new patient");
    }

    /**
     * Handles editing the selected patient
     */
    @FXML
    private void handleEdit(ActionEvent event) {
        Patient selectedPatient = patientTable.getSelectionModel().getSelectedItem();

        if (selectedPatient != null) {
            setFieldsEditable(true);
            isEditMode = true;
            lastNameField.requestFocus();
            statusLabel.setText("Editing patient: " + selectedPatient.getFirstName() + " " + selectedPatient.getSurname());
        } else {
            showAlert("No Selection", "No Patient Selected", "Please select a patient to edit.", Alert.AlertType.INFORMATION);
        }
    }


    @FXML
    private void handleDelete(ActionEvent event) {
        Patient selectedPatient = patientTable.getSelectionModel().getSelectedItem();

        if (selectedPatient != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Delete Patient: " + selectedPatient.getFirstName() + " " + selectedPatient.getSurname());
            alert.setContentText("Are you sure you want to delete this patient? This action cannot be undone.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean deleted = patientService.deletePatient(selectedPatient.getPatientId());

                if (deleted) {
                    patientList.remove(selectedPatient);
                    clearFields();
                    statusLabel.setText("Patient deleted");
                } else {
                    showErrorDialog("Delete Failed", "Failed to delete patient",
                            "The patient may have related records that prevent deletion.");
                }
            }
        } else {
            showAlert("No Selection", "No Patient Selected", "Please select a patient to delete.", Alert.AlertType.INFORMATION);
        }
    }

    /**
     * Handles saving the current patient
     */
    @FXML
    private void handleSave(ActionEvent event) {
        // Validate form data
        if (lastNameField.getText().trim().isEmpty() || firstNameField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Missing Required Fields",
                    "Please enter values for First Name and Last Name.", Alert.AlertType.WARNING);
            return;
        }

        if (phoneField.getText().trim().isEmpty()) {
            showAlert("Validation Error", "Missing Required Field",
                    "Please enter a phone number.", Alert.AlertType.WARNING);
            return;
        }

        // Create or update patient
        Patient patient = new Patient();
        patient.setSurname(lastNameField.getText().trim());
        patient.setFirstName(firstNameField.getText().trim());
        patient.setAddress(addressField.getText().trim());
        patient.setTelephone(phoneField.getText().trim());

        if (isEditMode) {
            // Update existing patient
            patient.setPatientId(Integer.parseInt(idField.getText()));
            boolean updated = patientService.updatePatient(patient);

            if (updated) {
                // Update the item in the list
                int index = patientList.indexOf(patientTable.getSelectionModel().getSelectedItem());
                patientList.set(index, patient);
                patientTable.refresh();
                statusLabel.setText("Patient updated");

                // Disable editing
                setFieldsEditable(false);
            } else {
                showErrorDialog("Update Failed", "Failed to update patient",
                        "An error occurred while updating the patient.");
                statusLabel.setText("Update failed");
            }
        } else {
            // Add new patient
            Patient newPatient = patientService.addPatient(patient);

            if (newPatient.getPatientId() > 0) {
                patient.setPatientId(newPatient.getPatientId());
                patientList.add(patient);
                patientTable.getSelectionModel().select(patient);
                statusLabel.setText("Patient added: ID " + newPatient.getPatientId() + " " + newPatient.getSurname() + " " + newPatient.getFirstName() );

                // Disable editing
                setFieldsEditable(false);
            } else {
                showErrorDialog("Add Failed", "Failed to add patient",
                        "An error occurred while adding the patient.");
                statusLabel.setText("Add failed");
            }
        }
    }

    /**
     * Handles canceling the current operation
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        if (isEditMode) {
            // Revert to original values
            Patient selectedPatient = patientTable.getSelectionModel().getSelectedItem();
            showPatientDetails(selectedPatient);
        } else {
            // Clear form if adding new
            clearFields();
        }

        setFieldsEditable(false);
        statusLabel.setText("Operation canceled");
    }

    /**
     * Handles refreshing the patient list
     */
    @FXML
    private void handleRefresh(ActionEvent event) {
        loadPatientData();
        clearFields();
    }


    @FXML
    private void handleSearch(ActionEvent event) {
        String searchTerm = searchField.getText().trim();

        if (!searchTerm.isEmpty()) {
            List<Patient> results = patientService.searchPatientsByName(searchTerm);
            patientList = FXCollections.observableArrayList(results);
            patientTable.setItems(patientList);
            statusLabel.setText("Found " + results.size() + " matches for '" + searchTerm + "'");
        } else {
            loadPatientData();
        }
    }

    /**
     * Handles clearing the search field and resetting the table
     */
    @FXML
    private void handleClearSearch(ActionEvent event) {
        searchField.clear();
        loadPatientData();
        statusLabel.setText("Search cleared");
    }

    /**
     * Handles viewing details of hospitalizations for the selected patient
     */
//    @FXML
//    private void handleViewHospitalizations(ActionEvent event) {
//        Patient selectedPatient = patientTable.getSelectionModel().getSelectedItem();
//
//        if (selectedPatient != null) {
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ibra/frontend/view/HospitalizationView.fxml"));
//                Parent hospitalizationView = loader.load();
//
//                // Get the controller and pass the selected patient
//                HospitalizationController controller = loader.getController();
//                controller.setPatient(selectedPatient);
//
//                // Display in a new window or in the current scene
//                // For example, in the same scene:
//                BorderPane mainPane = (BorderPane) patientTable.getScene().getRoot();
//                mainPane.setCenter(hospitalizationView);
//
//                statusLabel.setText("Viewing hospitalizations for " + selectedPatient.getFirstName() + " " + selectedPatient.getSurname());
//            } catch (IOException e) {
//                e.printStackTrace();
//                showErrorDialog("Navigation Error", "Failed to load hospitalization view", e.getMessage());
//            }
//        } else {
//            showAlert("No Selection", "No Patient Selected", "Please select a patient to view hospitalizations.", Alert.AlertType.INFORMATION);
//        }
//    }

    /**
     * Shows an alert dialog with the specified parameters
     */
    private void showAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Shows an error dialog with the specified parameters
     */
    private void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Handles exporting patient data to CSV
     */
//    @FXML
//    private void handleExport(ActionEvent event) {
//        try {
//            // Simple implementation - could be enhanced with file chooser
//            boolean success = patientService.exportPatientsToCSV("patients_export.csv");
//
//            if (success) {
//                showAlert("Export Successful", "Data Exported",
//                        "Patient data has been exported to patients_export.csv", Alert.AlertType.INFORMATION);
//            } else {
//                showErrorDialog("Export Failed", "Failed to export data",
//                        "An error occurred during the export process.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            showErrorDialog("Export Error", "Failed to export data", e.getMessage());
//        }
//    }

    /**
     * Handles printing the current patient list
     */
    @FXML
    private void handlePrint(ActionEvent event) {
        try {
            // Placeholder for print functionality
            statusLabel.setText("Preparing to print patient list...");
            // Actual implementation would connect to printing services
            showAlert("Print", "Print Job Submitted",
                    "The patient list has been sent to the printer.", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Print Error", "Failed to print", e.getMessage());
        }
    }

    /**
     * Navigates back to the main dashboard
     */
    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        try {
            Parent dashboardView = FXMLLoader.load(getClass().getResource("/com/ibra/frontend/view/Dashboard.fxml"));
            BorderPane mainPane = (BorderPane) patientTable.getScene().getRoot();
            mainPane.setCenter(dashboardView);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Navigation Error", "Failed to load dashboard", e.getMessage());
        }
    }
}