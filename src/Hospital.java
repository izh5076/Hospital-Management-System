import People.Doctor;
import People.Nurse;
import People.Patient.Patient;
import Time.Appointment.Appointment;
import Time.Appointment.InvalidDateException;
import Time.Schedule;
import Time.TimeSlotFilledException;

import java.io.*;
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
    private static Schedule schedule;
    private static ArrayList<Patient> patients;
    private static ArrayList<Nurse> nurses;
    private static ArrayList<Doctor> doctors;
    private static ArrayList<String> insurances;
    private static Scanner input = new Scanner( System.in );

    /**
     * runs through setting up all initial info
     */
    public static void setupInitialInfo(Scanner input){
        input = new Scanner( System.in );
        System.out.println("add a (d)octor, (n)urse, (i)nsurance, or (q)uit");
        String choice = input.nextLine();
        while(true){
            if(choice.equalsIgnoreCase("d")){
                makeDoctor();
            }else if(choice.equalsIgnoreCase("n")){
                makeNurse();
            }else if(choice.equalsIgnoreCase("i")){
                makeInsurance();
            }else if(choice.equalsIgnoreCase("q")){
                break;
            }else{
                System.out.println("Bad entry.");
            }
        }
    }

    /**
     * checks and adds a new insurance type to the list
     */
    public static void makeInsurance(){
        input = new Scanner( System.in );
        while(true){
            System.out.println("Enter name of Insurance.");
            String choice = input.nextLine();
            if(choice.isEmpty() == false){
                insurances.add(choice);
                System.out.println("You have added Insurance: " + choice);
                break;
            }
        }
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

        Patient patient = new Patient(name, id, gender);
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

        Doctor doctor = new Doctor(name, gender, insurances);
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

        return new Nurse(name, gender);
    }

    /**
     * will get details for and add appointment to schedule
     */
    public static void addAppointment()
    {
        Scanner scan = new Scanner(System.in);

        Scanner in = new Scanner( System.in );
        System.out.println("Choose the patient you are scheduling an appointment for (enter number)");
        for (int i = 0; i < patients.size(); i++) {
            System.out.println(i + ": " + patients.get(i).getName());
        }
        int p;
        Patient schedulingPatient;
        while(true){
            p = in.nextInt();
            if((p < 0) || (p > patients.size()-1) ){
                System.out.println("Bad entry, try again.");
            }
            else{
                schedulingPatient = patients.get(p);
                break;
            }
        }

        int startTime;
        int day;
        int month;
        int year;
        Appointment a;
        while(true){
            System.out.println("Input the day of your appointment: ");
            day = scan.nextInt();

            System.out.println("Input the month of your appointment: ");
            month = scan.nextInt();

            System.out.println("Input the year of your appointment: ");
            year = scan.nextInt();

            System.out.println("What time would you like your appointment to be? ");
            startTime = scan.nextInt();

            try
            {
                a = new Appointment(day, month, year, startTime, schedulingPatient);
                schedule.add(a);
                break;
            }
            catch (InvalidDateException e)
            {
                System.err.println(e);
            }
            catch (TimeSlotFilledException e)
            {
                System.err.println(e);
            }
        }

    }

    /**
     * get and show schedule information
     */
    public static void showSchedule()
    {
        schedule.displaySchedule();
    }

    /**
     * chooses the patient and shows the information
     */
    public static void getPatientHistory()
    {
        boolean found = true;
        Scanner scan = new Scanner(System.in);
        int patientID;


        System.out.println("Enter patient ID to get the patient history: ");
        patientID = scan.nextInt();

        for (int i = 0; i < patients.size(); i++)
        {
            if(patientID == patients.get(i).getPatientID())
            {
                System.out.println(patients.get(i).getName() + "'s Appointments are as followed: ");
                for(int x = 0; x < patients.get(i).getChart().getAppointments().size(); x++)
                {
                    System.out.println("Appointment Time:" + patients.get(i).getChart().getAppointment(x).
                            getAppointmentTime() + " Appointment date: " + patients.get(i).getChart().getAppointment(x).
                        getAppointmentDate().toString() + " ");
                }

                System.out.println(patients.get(i).getName() + "'s illnesses are as followed");
                for (int j = 0; j < patients.get(i).getChart().getIllnesses().size(); j++)
                {
                    System.out.println(patients.get(i).getChart().getIllness(j) + " ");
                }

                System.out.println(patients.get(i).getName() + "'s medicines are as follows: ");
                for (int j = 0; j < patients.get(i).getChart().getMedicine().size(); j++)
                {
                    System.out.println(patients.get(i).getChart().getIllness(j) + " ");
                }
                found = true;
                break;
            }
            found = false;
        }
        if(!found)
        {
            System.out.println("Patient not found! ");
        }
    }

    /**
     * Main will handle branching to method-encapsulated logic
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner( System.in );
        boolean resume;
        while(true){
            System.out.println("Do you want to resume where you left off, if you ran this before? 'y' for yes, 'n' for no or haven't run before.");
            if(input.nextLine().equalsIgnoreCase("y")){
                resume = true;
                break;
            }else if (input.nextLine().equalsIgnoreCase("n")){
                resume = false;
                break;
            }else{
                System.out.println("Bad entry.");
            }
        }

        // Opens save file, will copy inputs to be entered upon reloading
        BufferedWriter writer = new BufferedWriter( new FileWriter("save.txt"));


        // if you want to resume, it changes source to save file
        if(resume){
            input = new Scanner("save.txt");
        }

        boolean firstRun = true;

        // Break into action options
        input = new Scanner( System.in );
        String choice;
        while(true){
            if(resume){
                if(firstRun){
                    setupInitialInfo(input);
                    firstRun = false;
                }
                if(input.hasNext() == false){
                    input = new Scanner( System.in );
                    resume = false;
                }
            }
            System.out.println("Would you like to (a)dd a patient, (s)chedule an appointment, (g)et upcoming schedule, get patient (h)istory, save and (q)uit");
            choice = input.nextLine();
            if(choice.equalsIgnoreCase("a")){
                writer.append("\n"+choice);
                makePatient();
            }else if( choice.equalsIgnoreCase("s")){
                writer.append("\n"+choice);
                addAppointment();
            }else if( choice.equalsIgnoreCase("g")){
                writer.append("\n"+choice);
                showSchedule();
            }else if( choice.equalsIgnoreCase("h")){
                writer.append("\n"+choice);
                getPatientHistory();
            }else if( choice.equalsIgnoreCase("q")){

                writer.append("\n"+choice);
                System.out.println(writer);
                //saveState();
                break;
            }else{
                System.out.println("Invalid input, restart");
            }
        }

    }
}
