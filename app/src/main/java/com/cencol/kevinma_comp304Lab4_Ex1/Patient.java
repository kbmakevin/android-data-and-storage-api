package com.cencol.kevinma_comp304Lab4_Ex1;

/**
 * Entity representing Patient table in the database
 */
public class Patient {
    private int patientId;
    private String firstName;
    private String lastName;
    private String department;
    private int doctorId;
    //String because can have alphanumeric characters e.g. A-230, D-132, etc.
    private String room;

    public Patient() {
    }

    public Patient(int patientId, String firstName, String lastName, String department, int doctorId, String room) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.doctorId = doctorId;
        this.room = room;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
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

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (Patient ID = " + patientId + ")";
    }
}
