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

    public Doctor(String name, String gender, ArrayList<String> insuranceType)
    {
        super(name, gender);
        this.insuranceType = insuranceType;
    }

    public void addPatientMed(String name, String medicine)
    {
        for(int i = 0; i < this.patients.size(); i++)
        {
            if(patients.get(i).name == name)
            {
                patients.get(i).addMed(medicine);
                break;
            }
        }
    }

    public void addPatient(Patient patient){
        this.patients.add(patient);
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
