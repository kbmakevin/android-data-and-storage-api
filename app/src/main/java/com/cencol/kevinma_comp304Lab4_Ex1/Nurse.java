package com.cencol.kevinma_comp304Lab4_Ex1;

/**
 * Entity representing Nurse table in the database
 */
public class Nurse {

    //private fields
    private int nurseId;
    private String firstName;
    private String lastName;
    private String department;
    private String password;

    //no arg constructor

    public Nurse() {
    }

    //constructor with args

    public Nurse(int nurseId, String firstName, String lastName, String department, String password) {
        this.nurseId = nurseId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.password = password;
    }


    //getter and setter methods

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
