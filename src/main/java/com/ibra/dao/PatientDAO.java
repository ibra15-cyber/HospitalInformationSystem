package com.ibra.dao;


import com.ibra.dbConnection.DBConnection;
import com.ibra.entity.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    private static final Logger logger = LoggerFactory.getLogger(PatientDAO.class);

    public Patient insertPatient(Patient patient) {
        String sql = "INSERT INTO Patient (surname, first_name, address, telephone) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, patient.getSurname());
            pstmt.setString(2, patient.getFirstName());
            pstmt.setString(3, patient.getAddress());
            pstmt.setString(4, patient.getTelephone());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                logger.error("Patient insert failed");
                throw new SQLException("Creating patient failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    patient.setPatientId(generatedKeys.getInt(1));
                    return patient;
                } else {
                    logger.error("Patient insert failed");
                    throw new SQLException("Creating patient failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error inserting patient: " + e.getMessage());
            logger.error("Error inserting patient: " + e.getMessage());
            return null;
        }
    }

    public Patient getPatientById(int patientId) {
        String sql = "SELECT * FROM Patient WHERE patient_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, patientId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPatient(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving patient: " + e.getMessage());
        }

        return null;
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM Patient";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                patients.add(mapResultSetToPatient(rs));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving patients: " + e.getMessage());
        }

        return patients;
    }


    public boolean updatePatient(Patient patient) {
        String sql = "UPDATE Patient SET surname = ?, first_name = ?, address = ?, telephone = ? WHERE patient_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, patient.getSurname());
            pstmt.setString(2, patient.getFirstName());
            pstmt.setString(3, patient.getAddress());
            pstmt.setString(4, patient.getTelephone());
            pstmt.setInt(5, patient.getPatientId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.error("Error updating patient: " + e.getMessage());
            return false;
        }
    }

    public boolean deletePatient(int patientId) {
        String sql = "DELETE FROM Patient WHERE patient_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, patientId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            logger.error("Error deleting patient: " + e.getMessage());
            return false;
        }
    }

    public List<Patient> searchPatientsByName(String name) {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM Patient WHERE surname LIKE ? OR first_name LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String searchPattern = "%" + name + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    patients.add(mapResultSetToPatient(rs));
                }
            }
        } catch (SQLException e) {
            logger.error("Error searching patients: " + e.getMessage());
        }

        return patients;
    }

    private Patient mapResultSetToPatient(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        patient.setPatientId(rs.getInt("patient_id"));
        patient.setSurname(rs.getString("surname"));
        patient.setFirstName(rs.getString("first_name"));
        patient.setAddress(rs.getString("address"));
        patient.setTelephone(rs.getString("telephone"));
        return patient;
    }
}
