package Patient;

import java.util.ArrayList;
import Appointment.*;

public class PatientHistory
{
    private ArrayList<String> illnesses;
    private ArrayList<String> medicine;
    private ArrayList<Appointment> appointments;

    public PatientHistory()
    {
        this.illnesses = new ArrayList<>();
        this.medicine = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    public void addAppointment(Appointment appointment)
    {
        this.appointments.add(appointment);
    }

    public void addIllness(String newIllness)
    {
        this.illnesses.add(newIllness);
    }

    public void addMedicine(String newMed)
    {
        this.medicine.add(newMed);
    }

    public ArrayList<Appointment> getAppointments()
    {
        return this.appointments;
    }

    public ArrayList<String> getIllnesses()
    {
        return this.illnesses;
    }

    public ArrayList<String> getMedicine()
    {
        return this.medicine;
    }
}
