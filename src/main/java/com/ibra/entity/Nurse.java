package com.ibra.entity;

public class Nurse extends Employee {
    private String rotation;
    private double salary;
    private String deptCode;


    public Nurse(int empId, String surname, String firstName, String address, String telephone,
                 String rotation, double salary, String deptCode) {
        super(empId, surname, firstName, address, telephone);
        this.rotation = rotation;
        this.salary = salary;
        this.deptCode = deptCode;
    }

    public String getRotation() {
        return rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "empId=" + getEmpId() +
                ", surname='" + getSurname() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", rotation='" + rotation + '\'' +
                ", salary=" + salary +
                ", deptCode='" + deptCode + '\'' +
                '}';
    }
}