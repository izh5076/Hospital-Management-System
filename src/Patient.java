import java.util.Random;

public class Patient extends Person implements InsuranceHolder
{
    private String insuranceType;
    private int patientID;
    private PatientHistory chart;

    public Patient(String name, String gender, int ID)
    {
        super(name, gender);
        this.chart = new PatientHistory();
        this.setPatientID(ID);
    }

    public void setPatientID(int ID)
    {
        this.patientID = ID;
    }
    public void addIllness(String illness)
    {
        this.chart.addIllness(illness);
    }

    public void addMed(String medicine)
    {
        this.chart.addMedicine(medicine);
    }

    public void addAppointment(Appointment a)
    {
        this.chart.addAppointment(a);
    }

    @Override
    public void setInsuranceType(String type)
    {
        this.insuranceType = type;
    }

    @Override
    public String getInsuranceType()
    {
        return this.insuranceType;
    }

}
