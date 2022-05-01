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
    private static Schedule schedule = new Schedule();
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Nurse> nurses = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<String> insurances = new ArrayList<>();
    private static Scanner input = new Scanner( System.in );
    private static SaveData saveData;

    /**
     * runs through setting up all initial info
     */
    public static void setupInitialInfo(){
        Scanner input = new Scanner( System.in );
        while(true)
        {
            System.out.println("add a (d)octor, (n)urse, (i)nsurance, or (q)uit");
            String choice = input.nextLine();

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
     * This will make an instance of SaveData and instantiate it, then uses output streams to write that object to "save.dat"
     */
    public static void saveState(){
        try {
            FileOutputStream fos = new FileOutputStream( "resources/save.dat");
            BufferedOutputStream bos = new BufferedOutputStream( fos );
            ObjectOutputStream oos = new ObjectOutputStream( bos );

            SaveData data = new SaveData( Hospital.schedule, Hospital.patients, Hospital.nurses, Hospital.doctors, Hospital.insurances );

            oos.writeObject(data);
            oos.close();

            System.out.println("Data saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads the SaveData object from "save.dat" and sets the variables
     */
    public static void loadState(){
        try {
            FileInputStream fis = new FileInputStream("resources/save.dat");
            BufferedInputStream bis = new BufferedInputStream( fis );
            ObjectInputStream ois = new ObjectInputStream( bis );

            SaveData data = (SaveData)ois.readObject();

            Hospital.schedule = data.getSchedule();
            Hospital.patients = data.getPatients();
            Hospital.nurses = data.getNurses();
            Hospital.doctors = data.getDoctors();
            Hospital.insurances = data.getInsurances();

            ois.close();

            System.out.println("load state has been called and executed");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("No save file found, defaulting to set up mode.");
            setupInitialInfo();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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
            Hospital.insurances.add(choice);
            System.out.println("You have added Insurance: " + choice);
            break;
        }
    }

    /**
     * Makes up a new Patient object
     *
     * @return the new Patient object
     */
    public static void makePatient(){
        Scanner input = new Scanner( System.in );
        System.out.println("Enter patient name: ");
        String name = input.nextLine();
        System.out.println("Enter patient gender, (m)ale, (f)emale, (o)ther");
        String gender = input.nextLine();
        while(!gender.equalsIgnoreCase("m") && !gender.equalsIgnoreCase("f") && !gender.equalsIgnoreCase("o"))
        {
            System.out.println("Invalid input");
            System.out.println("Enter patient gender, (m)ale, (f)emale, (o)ther");
            gender = input.nextLine();
        }

        ArrayList<String> patientInsurances = new ArrayList<>();
        System.out.println("Enter the Insurance the patient has (New Insurances will be added to system.)");
        String insurance = input.nextLine();
        patientInsurances.add(insurance);
        if(!Hospital.insurances.contains(insurance)){
            Hospital.insurances.add(insurance);
        }
        while(true){
            System.out.println("Enter another Insurance option for this patient, if no more, enter \"done\".");
            insurance = input.nextLine();
            if( insurance.equalsIgnoreCase("done")){
                break;
            }
            patientInsurances.add(insurance);
            if(!Hospital.insurances.contains(insurance)){
                Hospital.insurances.add(insurance);
            }
        }

        ArrayList<Doctor> possibleDoctors = new ArrayList<>();
        int n = 1;
        System.out.println("Choose the patients doctor (type the number): ");

        for (int i = 0; i < insurances.size(); i++)
        {
            for (int j = 0; j < doctors.size(); j++)
            {
                if(doctors.get(j).getInsuranceType().contains(insurances.get(i)))
                {
                    possibleDoctors.add(doctors.get(j));
                    System.out.println(n + ". " + doctors.get(j).getName());
                    n++;
                }
            }
        }
        int docNum = input.nextInt();
        Doctor patientDoctor;
        if(possibleDoctors.size()>0){
            patientDoctor = possibleDoctors.get(docNum - 1);
        }else{
            System.out.println("No doctor in patient's insurance network found, please add a new doctor and try again. Patient creation cancelled.");
            return;
        }

        Nurse patientNurse;
        if(nurses.size()>0){
            System.out.println("Choose the patients nurse");
            for (int i = 0; i < nurses.size(); i++)
            {
                System.out.println(i + 1 + ". " + nurses.get(i).getName());
            }
            int nurseNum = input.nextInt();
            patientNurse = nurses.get(nurseNum - 1);
        }else{
            System.out.println("No nurses found, making a new nurse that will be assigned to patient");
            makeNurse();
            patientNurse = nurses.get(nurses.lastIndexOf(nurses));
        }

        Patient patient = new Patient(name, gender, patientDoctor, patientNurse, insurances);
        System.out.println("Patient: " + name + " has been added.");
        Hospital.patients.add(patient);
    }

    /**
     * goes through making a Doctor object
     *
     * @return the new Doctor object
     */
    public static void makeDoctor(){
        Scanner input = new Scanner( System.in );
        System.out.println("Enter Doctor name: ");
        String name = input.nextLine();
        System.out.println("Enter Doctor gender, (m)ale, (f)emale, (o)ther");
        String gender = input.nextLine();
        while(!gender.equalsIgnoreCase("m") && !gender.equalsIgnoreCase("f") && !gender.equalsIgnoreCase("o"))
        {
            System.out.println("Invalid input");
            System.out.println("Enter patient gender, (m)ale, (f)emale, (o)ther");
            gender = input.nextLine();
        }

        ArrayList<String> doctorInsurances = new ArrayList<>();
        System.out.println("Enter the Insurance the doctor takes (Insurance will be added to system if not already)");
        String insurance = input.nextLine();
        doctorInsurances.add(insurance);
        if(!insurances.contains(insurance)){
            insurances.add(insurance);
        }
        while(true){
            System.out.println("Enter another Insurance option for this doctor, if no more, enter \"done\".");
            insurance = input.nextLine();
            if( insurance.equalsIgnoreCase("done")){
                break;
            }
            if(!doctorInsurances.contains(insurance)){
                doctorInsurances.add(insurance);
            }
            if(!insurances.contains(insurance)){
                insurances.add(insurance);
            }
        }

        Doctor doctor = new Doctor(name, gender, doctorInsurances);
        System.out.println(doctor);
        doctors.add(doctor);
    }

    /**
     * runs through the steps of making a Nurse object
     *
     * @return a Nurse object
     */
    public static void makeNurse(){
        Scanner input = new Scanner( System.in );
        System.out.println("Enter Nurse name: ");
        String name = input.nextLine();
        System.out.println("Enter Nurse gender, (m)ale, (f)emale, (o)ther");
        String gender = input.nextLine();
        while(!gender.equalsIgnoreCase("m") && !gender.equalsIgnoreCase("f") && !gender.equalsIgnoreCase("o"))
        {
            System.out.println("Invalid input");
            System.out.println("Enter patient gender, (m)ale, (f)emale, (o)ther");
            gender = input.nextLine();
        }
        Nurse nurse = new Nurse(name, gender);

        Hospital.nurses.add(nurse);
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
                schedulingPatient.getChart().addAppointment(a);
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

        System.out.println("You have added an appointment: " + a);
        System.out.println("Some follow up dates for the next appointment would be: ");
        for (int i = 0; i < 5; i++) {

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
        String patientName;


        System.out.println("Enter patient name to get the patient history: ");
        patientName = scan.nextLine();

        for (int i = 0; i < patients.size(); i++)
        {
            if(patients.get(i).getName() == patientName)
            {
                System.out.println(patients.get(i).getName() + "'s Appointments are as followed: ");
                for(int x = 0; x < patients.get(i).getChart().getAppointments().size(); x++)
                {
                    System.out.println("Appointment Time:" + patients.get(i).getChart().getAppointment(x).
                            getAppointmentTime() + " Appointment date: " + patients.get(i).getChart().getAppointment(x).
                        getAppointmentDate().toString() + " ");
                }

                System.out.println(patients.get(i).getName() + "'s illnesses are as followed: ");
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
        while(true){
            System.out.println("Do you want to resume where you left off, if you ran this before? 'y' for yes, 'n' for no or haven't run before.");
            String s = input.nextLine();
            if(s.equalsIgnoreCase("y")){
                loadState();
                break;
            }else if (s.equalsIgnoreCase("n")){
                setupInitialInfo();
                break;
            }else{
                System.out.println("Bad entry.");
            }
        }

        // Break into action options
        input = new Scanner( System.in );
        String choice;
        while(true){
            System.out.println("Would you like to (a)dd a patient, (s)chedule an appointment, (g)et upcoming schedule, get patient (h)istory, save and (q)uit, make new (d)octor, make new (n)urse, add new (i)insurance, (t)ests saved doctors and patients");
            choice = input.nextLine();
            if(choice.equalsIgnoreCase("a")){
                makePatient();
            }else if( choice.equalsIgnoreCase("s")){
                addAppointment();
            }else if( choice.equalsIgnoreCase("t")){
                System.out.println(Hospital.doctors);
                System.out.println(Hospital.patients);
            }else if( choice.equalsIgnoreCase("g")){
                showSchedule();
            }else if( choice.equalsIgnoreCase("d")){
                makeDoctor();
            }else if( choice.equalsIgnoreCase("n")){
                makeNurse();
            }else if( choice.equalsIgnoreCase("i")){
                makeInsurance();
            }else if( choice.equalsIgnoreCase("h")){
                getPatientHistory();
            }else if( choice.equalsIgnoreCase("q")){
                saveState();
                break;
            }else{
                System.out.println("Invalid input, restart");
            }
        }
        System.out.println("Thank you for using our Hospital Management System");
    }
}
