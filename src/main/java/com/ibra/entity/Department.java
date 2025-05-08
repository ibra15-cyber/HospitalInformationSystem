package com.ibra.entity;

public class Department {
    private String deptCode;
    private String name;
    private String building;
    private int directorId;


    public Department(String deptCode, String name, String building, int directorId) {
        this.deptCode = deptCode;
        this.name = name;
        this.building = building;
        this.directorId = directorId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptCode='" + deptCode + '\'' +
                ", name='" + name + '\'' +
                ", building='" + building + '\'' +
                ", directorId=" + directorId +
                '}';
    }
}
