package com.ibra.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class PatientServiceInterfaceTest {

    @Test
    @DisplayName("Test that PatientServiceImp implements PatientService")
    public void testServiceImplementation() {
        PatientServiceImp service = new PatientServiceImp();
        assertTrue(service instanceof PatientService, "PatientServiceImp should implement PatientService interface");
    }

    @Test
    @DisplayName("Test PatientServiceImp instantiation")
    public void testServiceInstantiation() {
        PatientServiceImp service = new PatientServiceImp();
        assertNotNull(service, "PatientServiceImp should be instantiated");
    }
}
