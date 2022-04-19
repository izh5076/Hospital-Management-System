package People.Patient;

import People.Person;
import Appointment.*;
import People.InsuranceHolder;

import java.util.ArrayList;

/**
 * People.Patient.java:
 *
 * Class that models a patient in a hospital
 *
 * @author Issac Heim
 * @author Christopher Brennen
 */
public class Patient extends Person implements InsuranceHolder
{
    // Instance Variables
    private ArrayList<String> insuranceType;
    private int patientID;
    private PatientHistory chart;

    /**
     * Constructor that takes in the name, gender and ID of the patient.
     *
     * @param name The name of the patient
     * @param gender The gender of the patient
     * @param ID The ID of the People.Patient
     */
    public Patient(String name, String gender, int ID)
    {
        super(name, gender);
        this.chart = new PatientHistory();
        this.setPatientID(ID);
    }

    /**
     * Mutator to set the patient ID of the patient
     *
     * @param ID The ID number of the patient
     */
    public void setPatientID(int ID)
    {
        this.patientID = ID;
    }

    /**
     * A mutator to add an illness to the patients chart
     *
     * @param illness The new illness that the patient has
     */
    public void addIllness(String illness)
    {
        this.chart.addIllness(illness);
    }

    /**
     * A mutator to add a new medicine to the patients chart
     *
     * @param medicine the new medicine of the patient
     */
    public void addMed(String medicine)
    {
        this.chart.addMedicine(medicine);
    }

    /**
     * A mutator to add an appointment to the patients chart
     *
     * @param a The Appointment object to be added to the patients chart
     */
    public void addAppointment(Appointment a)
    {
        this.chart.addAppointment(a);
    }

    public int getPatientID()
    {
        return patientID;
    }

    /**
     * Method from the InsuranceHolder interface that sets the type of insurance that the patient has
     *
     * @param type the Type of insurance that the patient has
     */
    @Override
    public void setInsuranceType(ArrayList<String> type)
    {
        this.insuranceType = type;
    }

    /**
     * Accessor method from the InsuranceHolder interface to get the type of insurance that the patient has
     * @return The type of insurance that the patient has
     */
    @Override
    public ArrayList<String> getInsuranceType()
    {
        return this.insuranceType;
    }

}
