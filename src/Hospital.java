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

    /**
     * runs through setting up all initial info
     */
    public static void setupInitialInfo(){
        Scanner input = new Scanner( System.in );
        System.out.println();
    }

    /**
     * will save all info to storage
     */
    public static void saveState(){

    }

    /**
     * will initialize all fields with data from memory
     */
    public static void setupFromMemory(){

    }

    /**
     * Makes up a new Patient object
     *
     * @return the new Patient object
     */
    public static Patient makePatient(){
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

    /**
     * goes through making a Doctor object
     *
     * @return the new Doctor object
     */
    public static Doctor makeDoctor(){
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

    /**
     * runs through the steps of making a Nurse object
     *
     * @return a Nurse object
     */
    public static Nurse makeNurse(){
        Scanner input = new Scanner( System.in );
        System.out.println("Enter Nurse name: ");
        String name = input.nextLine();
        System.out.println("Enter Nurse gender, (m)ale, (f)emale, (o)ther");
        String gender = input.nextLine();

        int id = Hospital.id;
        Hospital.id++;

        return new Nurse(name, gender, id);
    }

    /**
     * will get details for and add appointment to schedule
     */
    public static void addAppointment(){

    }

    /**
     * get and show schedule information
     */
    public static void showSchedule(){

    }

    /**
     * chooses the patient and shows the information
     */
    public static void getPatientHistory(){

    }

    /**
     * Main will handle branching to method-encapsulated logic
     *
     * @param args
     */
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
        String choice;
        while(true){
            System.out.println("Would you like to (a)dd a patient, (s)chedule an appointment, (g)et upcoming schedule, get patient (h)istory, save and (q)uit");
            choice = input.nextLine();
            if(choice.equalsIgnoreCase("a")){
                makePatient();
            }else if( choice.equalsIgnoreCase("s")){
                addAppointment();
            }else if( choice.equalsIgnoreCase("g")){
                showSchedule();
            }else if( choice.equalsIgnoreCase("h")){
                getPatientHistory();
            }else if( choice.equalsIgnoreCase("q")){
                saveState();
                break;
            }else{
                System.out.println("Invalid input, restart");
            }
        }

    }
}
