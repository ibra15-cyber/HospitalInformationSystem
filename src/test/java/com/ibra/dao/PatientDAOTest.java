package com.ibra.dao;

import com.ibra.entity.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PatientDAOTest {

    private PatientDAO patientDAO;
    private Patient testPatient;

    @BeforeEach
    public void setUp() {
        patientDAO = new PatientDAO();

        // Create a test patient
        testPatient = new Patient();
        testPatient.setSurname("Smith");
        testPatient.setFirstName("John");
        testPatient.setAddress("123 Test Street");
        testPatient.setTelephone("123-456-7890");
    }

    @AfterEach
    public void tearDown() {
        // Clean up any test patients created
        if (testPatient != null && testPatient.getPatientId() > 0) {
            patientDAO.deletePatient(testPatient.getPatientId());
        }
    }

    @Test
    public void testInsertPatient() {
        Patient insertedPatient = patientDAO.insertPatient(testPatient);
        assertNotNull(insertedPatient);
    }

    @Test
    public void testInsertPatientHasId() {
        Patient insertedPatient = patientDAO.insertPatient(testPatient);
        assertTrue(insertedPatient.getPatientId() > 0);
    }

    @Test
    public void testGetPatientById() {
        // First insert a patient to retrieve
        Patient insertedPatient = patientDAO.insertPatient(testPatient);

        // Then retrieve it
        Patient retrievedPatient = patientDAO.getPatientById(insertedPatient.getPatientId());
        assertEquals(insertedPatient.getPatientId(), retrievedPatient.getPatientId());
    }

    @Test
    public void testGetPatientByIdReturnsCorrectSurname() {
        Patient insertedPatient = patientDAO.insertPatient(testPatient);
        Patient retrievedPatient = patientDAO.getPatientById(insertedPatient.getPatientId());
        assertEquals(testPatient.getSurname(), retrievedPatient.getSurname());
    }

    @Test
    public void testGetPatientByIdReturnsCorrectFirstName() {
        Patient insertedPatient = patientDAO.insertPatient(testPatient);
        Patient retrievedPatient = patientDAO.getPatientById(insertedPatient.getPatientId());
        assertEquals(testPatient.getFirstName(), retrievedPatient.getFirstName());
    }

    @Test
    public void testGetPatientByIdReturnsCorrectAddress() {
        Patient insertedPatient = patientDAO.insertPatient(testPatient);
        Patient retrievedPatient = patientDAO.getPatientById(insertedPatient.getPatientId());
        assertEquals(testPatient.getAddress(), retrievedPatient.getAddress());
    }

    @Test
    public void testGetPatientByIdReturnsCorrectTelephone() {
        Patient insertedPatient = patientDAO.insertPatient(testPatient);
        Patient retrievedPatient = patientDAO.getPatientById(insertedPatient.getPatientId());
        assertEquals(testPatient.getTelephone(), retrievedPatient.getTelephone());
    }

    @Test
    public void testGetPatientByIdWithInvalidId() {
        Patient retrievedPatient = patientDAO.getPatientById(-1);
        assertNull(retrievedPatient);
    }

    @Test
    public void testGetAllPatients() {
        // Insert a test patient
        patientDAO.insertPatient(testPatient);

        List<Patient> patients = patientDAO.getAllPatients();
        assertFalse(patients.isEmpty());
    }

    @Test
    public void testUpdatePatient() {
        // First insert a patient
        Patient insertedPatient = patientDAO.insertPatient(testPatient);

        // Update the patient
        insertedPatient.setSurname("Johnson");
        boolean updateResult = patientDAO.updatePatient(insertedPatient);
        assertTrue(updateResult);
    }

    @Test
    public void testUpdatePatientChangesPersist() {
        // First insert a patient
        Patient insertedPatient = patientDAO.insertPatient(testPatient);

        // Update patient data
        String newSurname = "Johnson";
        insertedPatient.setSurname(newSurname);
        patientDAO.updatePatient(insertedPatient);

        // Retrieve patient and check updates
        Patient retrievedPatient = patientDAO.getPatientById(insertedPatient.getPatientId());
        assertEquals(newSurname, retrievedPatient.getSurname());
    }

    @Test
    public void testDeletePatient() {
        // First insert a patient
        Patient insertedPatient = patientDAO.insertPatient(testPatient);

        // Delete the patient
        boolean deleteResult = patientDAO.deletePatient(insertedPatient.getPatientId());
        assertTrue(deleteResult);
    }

    @Test
    public void testDeletePatientRemovesRecord() {
        // First insert a patient
        Patient insertedPatient = patientDAO.insertPatient(testPatient);
        int patientId = insertedPatient.getPatientId();

        // Delete the patient
        patientDAO.deletePatient(patientId);

        // Try to retrieve the deleted patient
        Patient retrievedPatient = patientDAO.getPatientById(patientId);
        assertNull(retrievedPatient);
    }

    @Test
    public void testSearchPatientsBySurname() {
        // First insert a patient with unique surname
        String uniqueSurname = "UniqueTestSurname" + System.currentTimeMillis();
        testPatient.setSurname(uniqueSurname);
        patientDAO.insertPatient(testPatient);

        // Search for the patient
        List<Patient> foundPatients = patientDAO.searchPatientsByName(uniqueSurname);
        assertEquals(1, foundPatients.size());
    }

    @Test
    public void testSearchPatientsByFirstName() {
        // First insert a patient with unique first name
        String uniqueFirstName = "UniqueTestName" + System.currentTimeMillis();
        testPatient.setFirstName(uniqueFirstName);
        patientDAO.insertPatient(testPatient);

        // Search for the patient
        List<Patient> foundPatients = patientDAO.searchPatientsByName(uniqueFirstName);
        assertEquals(1, foundPatients.size());
    }

    @Test
    public void testSearchPatientsWithNoMatches() {
        String nonExistentName = "ThisNameDoesNotExist" + System.currentTimeMillis();
        List<Patient> foundPatients = patientDAO.searchPatientsByName(nonExistentName);
        assertTrue(foundPatients.isEmpty());
    }
}