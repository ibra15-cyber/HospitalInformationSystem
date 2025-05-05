package com.ibra.entity;

public class Employee {
    private int empId;
    private String surname;
    private String firstName;
    private String address;
    private String telephone;


    public Employee(int empId, String surname, String firstName, String address, String telephone) {
        this.empId = empId;
        this.surname = surname;
        this.firstName = firstName;
        this.address = address;
        this.telephone = telephone;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
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
        return "Employee{" +
                "empId=" + empId +
                ", surname='" + surname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}