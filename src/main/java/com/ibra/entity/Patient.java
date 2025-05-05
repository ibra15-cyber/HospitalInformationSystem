package com.ibra.entity;

public class Patient {
    private int patientId;
    private String surname;
    private String firstName;
    private String address;
    private String telephone;


    public Patient(int patientId, String surname, String firstName, String address, String telephone) {
        this.patientId = patientId;
        this.surname = surname;
        this.firstName = firstName;
        this.address = address;
        this.telephone = telephone;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", surname='" + surname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}

