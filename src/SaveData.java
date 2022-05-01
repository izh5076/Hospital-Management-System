import People.Doctor;
import People.Nurse;
import People.Patient.Patient;
import Time.Schedule;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * SaveData.java:
 * This class holds all information that needs stored from main into one object
 *
 * @author Christopher Brennen
 * @author Issac Heim
 */
public class SaveData implements Serializable {
    private Schedule schedule;
    private ArrayList<Patient> patients;
    private ArrayList<Nurse> nurses;
    private ArrayList<Doctor> doctors;
    private ArrayList<String> insurances;

    public SaveData(Schedule schedule, ArrayList<Patient> patients, ArrayList<Nurse> nurses, ArrayList<Doctor> doctors, ArrayList<String> insurances) {
        this.schedule = schedule;
        this.patients = patients;
        this.nurses = nurses;
        this.doctors = doctors;
        this.insurances = insurances;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public ArrayList<Nurse> getNurses() {
        return nurses;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public ArrayList<String> getInsurances() {
        return insurances;
    }

}
