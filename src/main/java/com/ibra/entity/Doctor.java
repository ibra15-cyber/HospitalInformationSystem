package com.ibra.entity;

public class Doctor extends Employee {
    private String speciality;

    public Doctor(int empId, String surname, String firstName, String address, String telephone, String speciality) {
        super(empId, surname, firstName, address, telephone);
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "empId=" + getEmpId() +
                ", surname='" + getSurname() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", speciality='" + speciality + '\'' +
                '}';
    }
}
