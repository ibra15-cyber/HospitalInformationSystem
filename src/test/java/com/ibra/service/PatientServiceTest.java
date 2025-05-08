package com.ibra.service;

import com.ibra.entity.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PatientServiceTest {

    private PatientServiceImp patientService;
    private Patient testPatient;
    private int createdPatientId;

    @BeforeEach
    public void setUp() {
        patientService = new PatientServiceImp();
        testPatient = createTestPatient();
        Patient createdPatient = patientService.addPatient(testPatient);
        createdPatientId = createdPatient.getPatientId();
    }

    @AfterEach
    public void tearDown() {
        if (createdPatientId > 0) {
            patientService.deletePatient(createdPatientId);
        }
    }

    private Patient createTestPatient() {
        Patient patient = new Patient();
        patient.setSurname("ServiceTest");
        patient.setFirstName("TestPatient");
        patient.setAddress("123 Service Street");
        patient.setTelephone("555-4321");
        return patient;
    }

    // Add Patient Tests
    @Test
    @DisplayName("Adding valid patient returns non-null patient")
    public void addValidPatient_ReturnsPatient() {
        Patient newPatient = createTestPatient();
        newPatient.setSurname("Jones");
        Patient result = patientService.addPatient(newPatient);
        assertNotNull(result);
        patientService.deletePatient(result.getPatientId());
    }

    @Test
    @DisplayName("Adding patient with missing surname returns null")
    public void addPatient_MissingSurname_ReturnsNull() {
        Patient invalidPatient = createTestPatient();
        invalidPatient.setSurname(null);
        assertNull(patientService.addPatient(invalidPatient));
    }

    @Test
    @DisplayName("Adding patient with empty surname returns null")
    public void addPatient_EmptySurname_ReturnsNull() {
        Patient invalidPatient = createTestPatient();
        invalidPatient.setSurname("");
        assertNull(patientService.addPatient(invalidPatient));
    }

    @Test
    @DisplayName("Adding patient with missing first name returns null")
    public void addPatient_MissingFirstName_ReturnsNull() {
        Patient invalidPatient = createTestPatient();
        invalidPatient.setFirstName(null);
        assertNull(patientService.addPatient(invalidPatient));
    }

    @Test
    @DisplayName("Adding patient with missing telephone returns null")
    public void addPatient_MissingTelephone_ReturnsNull() {
        Patient invalidPatient = createTestPatient();
        invalidPatient.setTelephone(null);
        assertNull(patientService.addPatient(invalidPatient));
    }

    // Get Patient Tests
    @Test
    @DisplayName("Getting patient with valid ID returns patient")
    public void getPatient_ValidId_ReturnsPatient() {
        assertNotNull(patientService.getPatient(createdPatientId));
    }

    @Test
    @DisplayName("Getting patient with negative ID returns null")
    public void getPatient_NegativeId_ReturnsNull() {
        assertNull(patientService.getPatient(-1));
    }

    @Test
    @DisplayName("Getting patient with zero ID returns null")
    public void getPatient_ZeroId_ReturnsNull() {
        assertNull(patientService.getPatient(0));
    }

    // Get All Patients Tests
    @Test
    @DisplayName("Getting all patients returns non-empty list")
    public void getAllPatients_ReturnsNonEmptyList() {
        assertFalse(patientService.getAllPatients().isEmpty());
    }

    @Test
    @DisplayName("Getting all patients includes created patient")
    public void getAllPatients_IncludesCreatedPatient() {
        List<Patient> patients = patientService.getAllPatients();
        assertTrue(patients.stream().anyMatch(p -> p.getPatientId() == createdPatientId));
    }

    // Update Patient Tests
    @Test
    @DisplayName("Updating patient with valid data returns true")
    public void updatePatient_ValidData_ReturnsTrue() {
        Patient patient = patientService.getPatient(createdPatientId);
        patient.setSurname("Updated");
        assertTrue(patientService.updatePatient(patient));
    }

    @Test
    @DisplayName("Updating patient with empty surname returns false")
    public void updatePatient_EmptySurname_ReturnsFalse() {
        Patient patient = patientService.getPatient(createdPatientId);
        patient.setSurname("");
        assertFalse(patientService.updatePatient(patient));
    }

    @Test
    @DisplayName("Updating patient with negative ID returns false")
    public void updatePatient_NegativeId_ReturnsFalse() {
        Patient patient = createTestPatient();
        patient.setPatientId(-1);
        assertFalse(patientService.updatePatient(patient));
    }

    // Delete Patient Tests
    @Test
    @DisplayName("Deleting patient with valid ID returns true")
    public void deletePatient_ValidId_ReturnsTrue() {
        Patient patient = createTestPatient();
        patient = patientService.addPatient(patient);
        boolean result = patientService.deletePatient(patient.getPatientId());
        assertTrue(result);
    }

    @Test
    @DisplayName("Deleting patient with negative ID returns false")
    public void deletePatient_NegativeId_ReturnsFalse() {
        assertFalse(patientService.deletePatient(-1));
    }

    // Search Patients Tests
    @Test
    @DisplayName("Searching patients by name returns non-empty list")
    public void searchPatients_ValidName_ReturnsNonEmptyList() {
        assertFalse(patientService.searchPatientsByName("ServiceTest").isEmpty());
    }

    @Test
    @DisplayName("Searching patients by empty name returns empty list")
    public void searchPatients_EmptyName_ReturnsEmptyList() {
        assertTrue(patientService.searchPatientsByName("").isEmpty());
    }

    @Test
    @DisplayName("Searching patients by null name returns empty list")
    public void searchPatients_NullName_ReturnsEmptyList() {
        assertTrue(patientService.searchPatientsByName(null).isEmpty());
    }
}