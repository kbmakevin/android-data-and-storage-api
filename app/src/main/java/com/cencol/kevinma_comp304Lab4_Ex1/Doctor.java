package com.cencol.kevinma_comp304Lab4_Ex1;

/**
 * Entity representing Doctor table in the database
 */
public class Doctor {
    private int doctorId;
    private String firstName;
    private String lastName;
    private String department;
    private String password;

    public Doctor() {
    }

    public Doctor(int doctorId, String firstName, String lastName, String department, String password) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.password = password;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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

    @Override
    public String toString() {
        return "Doctor " + firstName + " " + lastName;
    }
}
