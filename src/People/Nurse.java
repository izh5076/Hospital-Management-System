package People;

import People.Patient.*;

import java.util.ArrayList;

/**
 * Nurse.java:
 *
 * Models the function of a nurse, nurses have access to patient histories, are able to access the schedule for patients
 * etc.
 *
 * @author Issac Heim
 * @author Christopher Brennen
 */
public class Nurse extends Person
{
    //instance variables

    //A list of patients that the nurse is responsible for
    protected ArrayList<Patient> patients;

    //The ID of the Nurse to be recognized by the program

    /**
     * Constructor that takes in th name of the nurse, the gender of the nurse, and the new nurses name
     *
     * @param name The name of the nurse
     * @param gender The gender of the nurse
     */
    public Nurse(String name, String gender)
    {
        super(name, gender);
    }

    /**
     * Constructor that takes in the name, the gender, the employee ID, and the list of patients the nurse has
     *
     * @param name The name of the nurse
     * @param gender The gender of the nurse
     * @param patients The list of nurses patients
     */
    public Nurse(String name, String gender, ArrayList<Patient> patients)
    {
        super(name, gender);
        this.patients = new ArrayList<Patient>();
        this.setNewEmployeePatients(patients);
    }


    /**
     * A mutator that accesses the ArrayList of the nurses patients and adds a new patient object
     *
     * @param newPatient the patient object to be added to the ArrayList of patients
     */
    public void addPatient(Patient newPatient)
    {
        this.patients.add(newPatient);
    }

    public String getName()
    {
        return this.name;
    }

    /**
     * A mutator to create a new list of patients for a new employee.
     * @param patients An ArrayList full of patient objects.
     */
    public void setNewEmployeePatients(ArrayList<Patient> patients)
    {
        this.patients = patients;
    }

    /**
     * An accessor to get the list of the nurses patients
     * @return The ArrayList of the Nurses Patients
     */
    public ArrayList<Patient> getPatients()
    {
        return this.patients;
    }
}
