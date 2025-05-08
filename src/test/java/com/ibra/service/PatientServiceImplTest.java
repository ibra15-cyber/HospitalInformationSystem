package com.ibra.service;

import com.ibra.dao.PatientDAO;
import com.ibra.entity.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class PatientServiceImplTest {

    @Mock
    private PatientDAO patientDAO;

    private PatientServiceImp patientService;

    private Patient validPatient;

    @BeforeEach
    public void setUp() {
        // Initialize the mocks
        MockitoAnnotations.openMocks(this);

        // Create the service with the mocked DAO
        patientService = new PatientServiceImp() {
            // Override the constructor-initialized DAO with our mock
            public PatientDAO getPatientDAO() {
                return patientDAO;
            }
        };

        // Create valid test patient
        validPatient = new Patient();
        validPatient.setPatientId(1);
        validPatient.setSurname("Smith");
        validPatient.setFirstName("John");
        validPatient.setAddress("123 Test Street");
        validPatient.setTelephone("123-456-7890");

        // Setup mock responses
        when(patientDAO.insertPatient(any(Patient.class))).thenReturn(validPatient);
        when(patientDAO.getPatientById(1)).thenReturn(validPatient);
        when(patientDAO.updatePatient(any(Patient.class))).thenReturn(true);
        when(patientDAO.deletePatient(1)).thenReturn(true);

        List<Patient> patientList = new ArrayList<>();
        patientList.add(validPatient);
        when(patientDAO.getAllPatients()).thenReturn(patientList);
        when(patientDAO.searchPatientsByName(anyString())).thenReturn(patientList);
    }

    @Test
    public void testAddPatientWithValidData() {
        Patient result = patientService.addPatient(validPatient);
        assertNotNull(result);
    }

    @Test
    public void testAddPatientWithNullSurname() {
        validPatient.setSurname(null);
        Patient result = patientService.addPatient(validPatient);
        assertNull(result);
    }

    @Test
    public void testAddPatientWithEmptySurname() {
        validPatient.setSurname("");
        Patient result = patientService.addPatient(validPatient);
        assertNull(result);
    }

    @Test
    public void testAddPatientWithNullFirstName() {
        validPatient.setFirstName(null);
        Patient result = patientService.addPatient(validPatient);
        assertNull(result);
    }

    @Test
    public void testAddPatientWithEmptyFirstName() {
        validPatient.setFirstName("");
        Patient result = patientService.addPatient(validPatient);
        assertNull(result);
    }

    @Test
    public void testAddPatientWithNullTelephone() {
        validPatient.setTelephone(null);
        Patient result = patientService.addPatient(validPatient);
        assertNull(result);
    }

    @Test
    public void testAddPatientWithEmptyTelephone() {
        validPatient.setTelephone("");
        Patient result = patientService.addPatient(validPatient);
        assertNull(result);
    }

    @Test
    public void testGetPatientWithInvalidId() {
        Patient result = patientService.getPatient(0);
        assertNull(result);
    }

    @Test
    public void testGetPatientWithNegativeId() {
        Patient result = patientService.getPatient(-1);
        assertNull(result);
    }

    @Test
    public void testGetAllPatients() {
        List<Patient> result = patientService.getAllPatients();
        assertFalse(result.isEmpty());
    }



    @Test
    public void testUpdatePatientWithInvalidId() {
        validPatient.setPatientId(0);
        boolean result = patientService.updatePatient(validPatient);
        assertFalse(result);
    }

    @Test
    public void testUpdatePatientWithNullSurname() {
        validPatient.setSurname(null);
        boolean result = patientService.updatePatient(validPatient);
        assertFalse(result);
    }

    @Test
    public void testUpdatePatientWithEmptySurname() {
        validPatient.setSurname("");
        boolean result = patientService.updatePatient(validPatient);
        assertFalse(result);
    }

    @Test
    public void testUpdatePatientWithNullFirstName() {
        validPatient.setFirstName(null);
        boolean result = patientService.updatePatient(validPatient);
        assertFalse(result);
    }

    @Test
    public void testUpdatePatientWithEmptyFirstName() {
        validPatient.setFirstName("");
        boolean result = patientService.updatePatient(validPatient);
        assertFalse(result);
    }

    @Test
    public void testUpdatePatientWithNullTelephone() {
        validPatient.setTelephone(null);
        boolean result = patientService.updatePatient(validPatient);
        assertFalse(result);
    }

    @Test
    public void testUpdatePatientWithEmptyTelephone() {
        validPatient.setTelephone("");
        boolean result = patientService.updatePatient(validPatient);
        assertFalse(result);
    }


    @Test
    public void testDeletePatientWithInvalidId() {
        boolean result = patientService.deletePatient(0);
        assertFalse(result);
    }

    @Test
    public void testDeletePatientWithNegativeId() {
        boolean result = patientService.deletePatient(-1);
        assertFalse(result);
    }

    @Test
    public void testSearchPatientsByValidName() {
        List<Patient> result = patientService.searchPatientsByName("Smith");
        assertFalse(result.isEmpty());
    }

    @Test
    public void testSearchPatientsByNullName() {
        List<Patient> result = patientService.searchPatientsByName(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSearchPatientsByEmptyName() {
        List<Patient> result = patientService.searchPatientsByName("");
        assertTrue(result.isEmpty());
    }
}