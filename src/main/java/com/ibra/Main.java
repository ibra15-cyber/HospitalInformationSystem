package com.ibra;

import com.ibra.dbConnection.DBConnection;
import com.ibra.entity.Patient;
import com.ibra.service.PatientService;
import com.ibra.service.PatientServiceImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
                PatientService patientService = new PatientServiceImp();

                Patient newPatient = new Patient();
                newPatient.setFirstName("John");
                newPatient.setSurname("Doe");
                newPatient.setAddress("Accra New Town");
                newPatient.setTelephone("555-123-4567");

                int patientId = patientService.addPatient(newPatient);

                System.out.println(patientId);

                // Get all patients
                List<Patient> allPatients = patientService.getAllPatients();
                for (Patient p : allPatients) {
                    System.out.println(p.getPatientId()+ " " + p.getFirstName() + " " + p.getSurname());
                }
        } finally {
            DBConnection.closeConnection();
        }
    }
}