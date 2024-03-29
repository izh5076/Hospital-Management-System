package People.Patient;

import java.io.Serializable;
import java.util.ArrayList;
import Time.Appointment.*;

/**
 * PatientHistory.java:
 *
 * A class that models the chart of a patient in a hospital. The chart contains illnesses, medicines, and patient
 * appointments.
 *
 * @author Issac Heim
 * @author Christopher Brennen
 */
public class PatientHistory implements Serializable
{
    //Instance Variables
    private ArrayList<String> illnesses;
    private ArrayList<String> medicine;
    private ArrayList<Appointment> appointments;

    /**
     * Default constructor to instantiate all fields
     */
    public PatientHistory()
    {
        this.illnesses = new ArrayList<>();
        this.medicine = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    /**
     * Mutator to add an appointment to the ArrayList of patient appointments
     *
     * @param appointment The appointment object to be added to the ArrayList of appointments
     */
    public void addAppointment(Appointment appointment)
    {
        this.appointments.add(appointment);
    }

    /**
     * Mutator to add a new illness to the ArrayList of Illnesses
     *
     * @param newIllness The illness to be added to the ArrayList
     */
    public void addIllness(String newIllness)
    {
        this.illnesses.add(newIllness);
    }

    public void setIllnesses(ArrayList<String> illnesses)
    {
        this.illnesses = illnesses;
    }

    public void setMedicine(ArrayList<String> medicines)
    {
        this.medicine = medicines;
    }

    public void setAppointments(ArrayList<Appointment> appointments)
    {
        this.appointments = appointments;
    }
    /**
     * Mutator to add a new medicine to the ArrayList of patient Medicines
     *
     * @param newMed The Medicine to be added to the ArrayList
     */
    public void addMedicine(String newMed)
    {
        this.medicine.add(newMed);
    }

    /**
     * An accessor to get the ArrayList of appointments of the patient
     *
     * @return The ArrayList of People.Patient appointments
     */
    public ArrayList<Appointment> getAppointments()
    {
        return this.appointments;
    }

    public Appointment getAppointment(int i)
    {
        return appointments.get(i);
    }

    /**
     * An accessor to get the illnesses of the patient
     *
     * @return The ArrayList of illnesses
     */
    public ArrayList<String> getIllnesses()
    {
        return this.illnesses;
    }

    public String getIllness(int i)
    {
        return this.illnesses.get(i);
    }

    /**
     * An accessor to get the medicines of the patient
     *
     * @return The ArrayList of medicines
     */
    public ArrayList<String> getMedicine()
    {
        return this.medicine;
    }

    public String getMedicine(int i)
    {
        return this.medicine.get(i);
    }
}
