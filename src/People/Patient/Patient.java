package People.Patient;

import People.Doctor;
import People.Nurse;
import People.Person;
import Time.Appointment.*;
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
    private PatientHistory chart;
    private Doctor patientDoctor;
    private Nurse patientNurse;


    /**
     * Constructor that takes in the name, gender and ID of the patient.
     *
     * @param name The name of the patient
     * @param gender The gender of the patient
     */
    public Patient(String name, String gender, Doctor d, Nurse n, ArrayList<String> insurances)
    {
        super(name, gender);
        this.chart = new PatientHistory();
        this.setDoctor(d);
        this.setNurse(n);
        this.setInsuranceType(insurances);
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
     * Setter to set the patients doctor.
     *
     * @param d a doctor object representing the doctor that the patient has
     */
    public void setDoctor(Doctor d)
    {
        this.patientDoctor = d;
    }

    public void setNurse(Nurse n)
    {
        this.patientNurse = n;
    }

    /**
     * A mutator to add an appointment to the patients chart
     *
     * @param a The Time.Appointment object to be added to the patients chart
     */
    public void addAppointment(Appointment a)
    {
        this.chart.addAppointment(a);
    }

    public String getName()
    {
        return this.name;
    }

    public PatientHistory getChart()
    {
        return this.chart;
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

    @Override
    public String toString() {
        return "Patient{ " + this.getName() +
                ", Insured by: " + insuranceType +
                ", " + patientDoctor +
                ", " + patientNurse +
                " }";
    }
}
