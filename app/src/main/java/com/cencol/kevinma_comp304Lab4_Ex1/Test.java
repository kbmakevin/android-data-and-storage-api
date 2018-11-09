package com.cencol.kevinma_comp304Lab4_Ex1;

/**
 * Entity representing Test table in the database
 * (where Test = Medical Tests Nurses perform on the Patients)
 */
public class Test {
    private int testId;
    private int patientId;
    private int nurseId;
    //https://www.webmd.com/hypertension-high-blood-pressure/guide/diastolic-and-systolic-blood-pressure-know-your-numbers#1
    //upper #; measured in mm Hg
    private int systolicBPL;
    //lower #; measured in mm Hg
    private int diastolicBPL;
    //Benign prostatic hyperplasia: A common, noncancerous enlargement of the prostate gland. The enlarged prostate may compress the urinary tube (urethra), which courses through the center of the prostate, impeding the flow of urine from the bladder through the urethra to the outside. Abbreviated BPH.
    //only enabled if patient is male; else this will be null value entry
    //https://www.medicinenet.com/script/main/art.asp?articlekey=8946
    private boolean BPH;
    //measured in celcius (metric)
    private double temperature;
    //measured in cm (metric)
    private double height;
    //measured in kg (metric)
    private double weight;
    private double BMI;

    public Test() {
    }

    public Test(int testId, int patientId, int nurseId, int systolicBPL, int diastolicBPL, boolean BPH, double temperature, double height, double weight, double BMI) {
        this.testId = testId;
        this.patientId = patientId;
        this.nurseId = nurseId;
        this.systolicBPL = systolicBPL;
        this.diastolicBPL = diastolicBPL;
        this.BPH = BPH;
        this.temperature = temperature;
        this.height = height;
        this.weight = weight;
        this.BMI = BMI;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public int getSystolicBPL() {
        return systolicBPL;
    }

    public void setSystolicBPL(int systolicBPL) {
        this.systolicBPL = systolicBPL;
    }

    public int getDiastolicBPL() {
        return diastolicBPL;
    }

    public void setDiastolicBPL(int diastolicBPL) {
        this.diastolicBPL = diastolicBPL;
    }

    public boolean isBPH() {
        return BPH;
    }

    public void setBPH(boolean BPH) {
        this.BPH = BPH;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    @Override
    public String toString() {
        return "(Test ID = " + testId + ")" + " for (Patient ID = " + patientId + ")";
    }
}
