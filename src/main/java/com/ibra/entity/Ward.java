package com.ibra.entity;

public class Ward {
    private int wardId;
    private int wardNumber;
    private int bedCount;
    private String deptCode;
    private int supervisorId;

    public Ward(int wardId, int wardNumber, int bedCount, String deptCode, int supervisorId) {
        this.wardId = wardId;
        this.wardNumber = wardNumber;
        this.bedCount = bedCount;
        this.deptCode = deptCode;
        this.supervisorId = supervisorId;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public int getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(int wardNumber) {
        this.wardNumber = wardNumber;
    }

    public int getBedCount() {
        return bedCount;
    }

    public void setBedCount(int bedCount) {
        this.bedCount = bedCount;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    @Override
    public String toString() {
        return "Ward{" +
        "wardId=" + wardId +
                ", wardNumber=" + wardNumber +
                ", bedCount=" + bedCount +
                ", deptCode='" + deptCode + '\'' +
                ", supervisorId=" + supervisorId +
                '}';
    }
}
