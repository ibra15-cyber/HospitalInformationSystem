package com.ibra;

import com.ibra.dbConnection.DBConnection;
import com.ibra.entity.Patient;
import com.ibra.frontend.HospitalAppUI;
import com.ibra.service.PatientService;
import com.ibra.service.PatientServiceImp;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
                HospitalAppUI.main(args);
        } finally {
            DBConnection.closeConnection();
        }
    }
}