package com.ibra.service;

import com.ibra.dao.PatientDAO;
import com.ibra.entity.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class PatientServiceImp implements PatientService {
    private final PatientDAO patientDAO;
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);


    public PatientServiceImp() {
        this.patientDAO = new PatientDAO();
    }

    @Override
    public Patient addPatient(Patient patient) {
        if (isNullOrEmpty(patient.getSurname()) || isNullOrEmpty(patient.getFirstName())) {
            logger.error("Patient first name and surname are required");
            return null;
        }

        if (isNullOrEmpty(patient.getTelephone())) {
            logger.error("Patient telephone is required");
            return null;
        }

        return patientDAO.insertPatient(patient);
    }

    @Override
    public Patient getPatient(int patientId) {
        if (patientId <= 0) {
            logger.error("Invalid patient ID");
            return null;
        }

        return patientDAO.getPatientById(patientId);
    }

    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    @Override
    public boolean updatePatient(Patient patient) {
        if (patient.getPatientId() <= 0) {
            logger.error("Invalid patient ID");
            return false;
        }

        if (isNullOrEmpty(patient.getSurname()) || isNullOrEmpty(patient.getFirstName())) {
            logger.error("Patient first name and surname are required");
            return false;
        }

        if (isNullOrEmpty(patient.getTelephone())) {
            System.err.println("Patient telephone is required");
            return false;
        }

        return patientDAO.updatePatient(patient);
    }

    @Override
    public boolean deletePatient(int patientId) {
        if (patientId <= 0) {
            System.err.println("Invalid patient ID");
            return false;
        }

        return patientDAO.deletePatient(patientId);
    }

    @Override
    public List<Patient> searchPatientsByName(String name) {
        if (isNullOrEmpty(name)) {
            logger.error("Search term cannot be empty");
            return List.of();
        }

        return patientDAO.searchPatientsByName(name);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}