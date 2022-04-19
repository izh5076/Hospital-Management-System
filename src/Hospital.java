import People.Doctor;
import People.Nurse;
import People.Patient.Patient;
import Time.Schedule;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hospital.java:
 * this will hold the main and method logic
 *
 * @author Christopher Brennen
 * @author Issac Heim
 */
public class Hospital {
    private Schedule schedule;
    private ArrayList<Patient> patients;
    private ArrayList<Nurse> nurses;
    private ArrayList<Doctor> doctors;
    private static int id = 0;

    public static void setupInitialInfo(){
        Scanner input = new Scanner( System.in );
        System.out.println();
    }
    public static void setupFromMemory(){

    }
    public static void getInfo(){

    }
    public static void setInfo(){

    }
    public static Patient setPatient(){
        Scanner input = new Scanner( System.in );
        System.out.println("Enter patient name: ");
        String name = input.nextLine();
        System.out.println("Enter patient gender, (m)ale, (f)emale, (o)ther");
        String gender = input.nextLine();

        int id = Hospital.id;
        Hospital.id++;

        Patient patient = new Patient(name, gender, id);
        return patient;
    }
    public static Doctor setDoctor(){
        Scanner input = new Scanner( System.in );
        System.out.println("Enter Doctor name: ");
        String name = input.nextLine();
        System.out.println("Enter Doctor gender, (m)ale, (f)emale, (o)ther");
        String gender = input.nextLine();

        int id = Hospital.id;
        Hospital.id++;

        ArrayList<String> insurances = new ArrayList<>();
        System.out.println("Enter the Insurance the doctor takes");
        String insurance = input.nextLine();
        insurances.add(insurance);
        while(true){
            System.out.println("Enter another Insurance option for this doctor, if no more, enter \"done\".");
            insurance = input.nextLine();
            if( insurance.equalsIgnoreCase("done")){
                break;
            }
            insurances.add(insurance);
        }

        Doctor doctor = new Doctor(name, gender, id, insurances);
        return doctor;
    }

    public static void main(String[] args) {
        // Setup initial information
        boolean firstRun = true;
        if(firstRun){
            setupInitialInfo();
        }else{
            setupFromMemory();
        }

        // Break into action options
        Scanner input = new Scanner( System.in );

    }
}
