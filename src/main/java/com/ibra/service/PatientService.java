package com.ibra.service;

import com.ibra.entity.Patient;

import java.util.List;

public interface PatientService {
    int addPatient(Patient patient);
    Patient getPatient(int patientId);
    List<Patient> getAllPatients();
    boolean updatePatient(Patient patient);
    boolean deletePatient(int patientId);
    List<Patient> searchPatientsByName(String name);
}