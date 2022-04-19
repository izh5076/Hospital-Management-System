package People;

import People.Patient.Patient;

import java.util.ArrayList;

/**
 * Doctor.java:
 * Doctor is a more specialized Nurse
 *
 * @author Issac Heim
 * @author Christopher Brennen
 */
public class Doctor extends Nurse implements InsuranceHolder
{
    private ArrayList<String> insuranceType;

    public Doctor(String name, String gender, int ID, ArrayList<Patient> patients)
    {
        super(name, gender, ID, patients);
    }

    public void addPatientMed(int patientID, String medicine)
    {

        for(int i = 0; i < this.patients.size(); i++)
        {
            if(patients.get(i).getPatientID() == patientID)
            {
                patients.get(i).addMed(medicine);
                break;
            }
        }
    }

    @Override
    public void setInsuranceType(ArrayList<String> type)
    {
        this.insuranceType = type;
    }

    @Override
    public ArrayList<String> getInsuranceType() {
        return this.insuranceType;
    }

}
