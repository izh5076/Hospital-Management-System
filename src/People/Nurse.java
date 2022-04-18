package People;

import Patient.*;

import java.util.ArrayList;

/**
 * Nurse.java:
 *
 * Models the function of a nurse, nurses have access to patient histories, are able to access the schedule for patients
 * etc.
 *
 * @author Issac Heim
 * @author Christopher Brennan
 */
public class Nurse extends Person
{
    //instance variables

    //A list of patients that the nurse is responsible for
    protected ArrayList<Patient> patients;

    //The ID of the Nurse to be recognized by the program
    private int employeeID;

    /**
     * Constructor that takes in th name of the nurse, the gender of the nurse, and the new nurses name
     *
     * @param name The name of the nurse
     * @param gender The gender of the nurse
     * @param ID The employee ID of the nurse
     */
    public Nurse(String name, String gender, int ID)
    {
        super(name, gender);
        this.employeeID = ID;
    }

    /**
     * Constructor that takes in the name, the gender, the employee ID, and the list of patients the nurse has
     *
     * @param name The name of the nurse
     * @param gender The gender of the nurse
     * @param ID The employee ID of the nurse
     * @param patients The list of nurses patients
     */
    public Nurse(String name, String gender, int ID, ArrayList<Patient> patients)
    {
        super(name, gender);
        this.employeeID = ID;
        this.patients = new ArrayList<Patient>();
        this.setNewEmployeePatients(patients);
    }

    /**
     * Mutator to set the ID of the nurse
     *
     * @param employeeID The new employee ID of the nurse
     */
    public void setEmployeeID(int employeeID)
    {
        this.employeeID = employeeID;
    }

    /**
     * Accessor to get the employee ID of the nurse
     *
     * @return The employee ID of the nurse
     */
    public int getEmployeeID()
    {
        return employeeID;
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
