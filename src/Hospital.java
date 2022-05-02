import People.Doctor;
import People.Nurse;
import People.Patient.Patient;
import Time.Appointment.Appointment;
import Time.Appointment.InvalidDateException;
import Time.MonthDays;
import Time.Schedule;
import Time.TimeSlotFilledException;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
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
    private static ArrayList<Patient> patients = new ArrayList<>() ;
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
            System.out.println("add a (d)octor, (n)urse, (i)nsurance, or (f)inished with setup info");
            String choice = input.nextLine();

            if(choice.equalsIgnoreCase("d")){
                makeDoctor();
            }else if(choice.equalsIgnoreCase("n")){
                makeNurse();
            }else if(choice.equalsIgnoreCase("i")){
                makeInsurance();
            }else if(choice.equalsIgnoreCase("f")){
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

            System.out.println("Previous state has been loaded.");
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
        System.out.println("Enter the Insurance the patient has.");
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
        System.out.println("Choose the patients doctor(enter number): ");

        for (Doctor d : Hospital.doctors) {
            for (String ins : d.getInsuranceType()){
                if(patientInsurances.contains(ins)){
                    if(!possibleDoctors.contains(d)){
                        possibleDoctors.add(d);
                        System.out.println(n + ". " + d.getName());
                        n++;
                    }
                }
            }

        }

        // replaced loop with above, should be far cleaner and the other one wasn't sorting by insurance right i dont think
        /*
        for (int i = 0; i < patientInsurances.size(); i++)
        {
            for (int j = 0; j < doctors.size(); j++)
            {
                if(doctors.get(j).getInsuranceType().contains(patientInsurances.get(i)))
                {
                    possibleDoctors.add(doctors.get(j));
                    System.out.println(n + ". " + doctors.get(j).getName());
                    n++;
                }
            }
        }
         */

        Doctor patientDoctor;
        if(!(possibleDoctors.size() >0)){
            System.out.println("The patient's insurance has no supported doctors in the system, patient cannot currently be registered. Patient creation cancelled.");
            return;

        }else{
            int docNum = input.nextInt();
            patientDoctor = possibleDoctors.get(docNum - 1);
        }

        Nurse patientNurse;
        if(nurses.size()>0){
            System.out.println("Choose the patients nurse (enter number)");
            for (int i = 0; i < nurses.size(); i++)
            {
                System.out.println(i + 1 + ". " + nurses.get(i).getName());
            }
            int nurseNum = input.nextInt();
            patientNurse = nurses.get(nurseNum - 1);
        }else{
            System.out.println("No nurses found, making a new nurse that will be assigned to patient");
            makeNurse();
            patientNurse = nurses.get(nurses.size()-1);
        }

        Patient patient = new Patient(name, gender, patientDoctor, patientNurse, patientInsurances);
        Hospital.patients.add(patient);
        makePatientHistory( Hospital.patients.size()-1);
        System.out.println("Patient: " + name + " has been registered.");

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
        if(!Hospital.insurances.contains(insurance)){
            Hospital.insurances.add(insurance);
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
            if(!Hospital.insurances.contains(insurance)){
                Hospital.insurances.add(insurance);
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
        for (int i = 1; i <= patients.size(); i++) {
            System.out.println(i + ": " + patients.get(i-1).getName());
        }
        int p;
        Patient schedulingPatient;
        while(true){
            p = in.nextInt();
            if((p < 1) || (p > patients.size())){
                System.out.println("Bad entry, try again.");
            }
            else{
                schedulingPatient = patients.get(p-1);
                break;
            }
        }

        int startTime;
        int day;
        int month;
        int year;
        Appointment a;
        while(true){
            System.out.println("Input the day of your appointment (integer): ");
            day = scan.nextInt();

            System.out.println("Input the month of your appointment (integer): ");
            month = scan.nextInt();

            System.out.println("Input the year of your appointment (integer): ");
            year = scan.nextInt();

            System.out.println("What time would you like your appointment to be? Open 9-17, military time. (enter integer).");
            startTime = scan.nextInt();
            month--;
            try
            {
                a = new Appointment(day, month, year, startTime, schedulingPatient);
                schedule.add(a);
                schedulingPatient.getChart().addAppointment(a);
                break;
            }
            catch (InvalidDateException | TimeSlotFilledException e)
            {
                System.err.println(e);
            }
        }

        // Follow-up Dates
        System.out.println("You have added an appointment: " + a);
        System.out.println("Some follow up dates for the patient's next appointment would be: ");
        for (int i = 1; i < 5; i++) {
            int nextDay = day + 1 + 7*i;
            int nextMonth = month;
            int nextYear = year;
            int daysInMonth = MonthDays.getDays(month);
            if(nextDay > daysInMonth){
                nextMonth++;
                nextDay = nextDay-MonthDays.getDays(month);
                if((nextMonth) > 12){
                    nextMonth = 1;
                    nextYear++;
                }
            }
            System.out.println("     " + i + " week(s) after: " + new Date(nextYear-1900, nextMonth, nextDay, startTime, 0) );
        }
    }

    /**
     * get and show schedule information
     */
    public static void showSchedule()
    {
        schedule.displaySchedule();
    }

    public static void makePatientHistory( int patientNum )
    {
        ArrayList<String> currentIllnesses = new ArrayList<>();
        ArrayList<String> currentMedicines = new ArrayList<>();
        ArrayList<Appointment> currentAppointments = new ArrayList<>(); ;
        while(true) {
            System.out.println("Enter patient's illnesses one by one (if done, enter \"done\")");
            String illness = input.nextLine();
            if (illness.equalsIgnoreCase("done")) {
                break;
            }
            if (!currentIllnesses.contains(illness))
            {
                currentIllnesses.add(illness);
            }
        }
        patients.get(patientNum).getChart().setIllnesses(currentIllnesses);

        while(true){
            System.out.println("Enter patient's medications one by one, or \"done\".");
            String med = input.nextLine();
            if(med.equalsIgnoreCase("done"))
            {
                break;
            }
            if(!currentMedicines.contains(med)){
                currentMedicines.add(med);
            }
        }
        patients.get(patientNum).getChart().setMedicine(currentMedicines);
    }

    /**
     * chooses the patient and shows the information
     */
    public static void getPatientHistory()
    {
        Patient patient;
        if(patients.size()>0){
            System.out.println("Choose the patient (enter number)");
            for (int i = 0; i < patients.size(); i++)
            {
                System.out.println(i + 1 + ". " + patients.get(i).getName());
            }
            int patientNum = input.nextInt();
            patient = patients.get(patientNum - 1);

            System.out.println(patient.getName() + "'s Appointments are as follows: ");
            for(int x = 0; x < patient.getChart().getAppointments().size(); x++)
            {
                System.out.println("Time: " + patient.getChart().getAppointment(x).
                        getAppointmentTime() + ", Date: " + patient.getChart().getAppointment(x).
                        getAppointmentDate().toString() + " ");
            }

            System.out.println(patient.getName() + "'s illnesses are as follows: ");
            for (int j = 0; j < patient.getChart().getIllnesses().size(); j++)
            {
                System.out.println(patient.getChart().getIllness(j) + " ");
            }

            System.out.println(patient.getName() + "'s medicines are as follows: ");
            for (int j = 0; j < patient.getChart().getMedicine().size(); j++)
            {
                System.out.println(patient.getChart().getIllness(j) + " ");
            }
        }else{
            System.out.println("No patients found, make a patient and try again.");
            return;
        }
    }

    /**
     * will choose the patient and output their info
     */
    public static void getPatientInfo(){
        if(patients.size()>0)
        {
            System.out.println("Choose the patient (enter number)");
            for (int i = 0; i < patients.size(); i++)
            {
                System.out.println(i + 1 + ". " + patients.get(i).getName());
            }
            int patientNum = input.nextInt();
            patientNum--;
            System.out.println(patients.get(patientNum).toString());
        }
        else
        {
            System.out.println("No patients found, make a patient and try again.");
            return;
        }
    }

    /**
     * Main will handle branching to method-encapsulated logic
     *
     * @param args
     */
    public static void main(String[] args) {
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
            System.out.println("\nWould you like to (enter number): \n1. add a patient, \n2. schedule an appointment, " +
                    "\n3. get upcoming schedule, \n4. get patient history, \n5. get patient info, \n6. make new doctor, " +
                    "\n7. make new nurse, \n8. add new insurance, \n9. save and quit, \n10. test data");
            choice = input.nextLine();
            if(choice.equalsIgnoreCase("1")){
                makePatient();
            }else if( choice.equalsIgnoreCase("2")){
                addAppointment();
            }else if( choice.equalsIgnoreCase("10")){
                System.out.println(Hospital.doctors);
                System.out.println(Hospital.patients);
                System.out.println(Hospital.insurances);
                System.out.println(Hospital.nurses);
                System.out.println(Hospital.schedule);
            }else if( choice.equalsIgnoreCase("3")){
                showSchedule();
            }else if( choice.equalsIgnoreCase("6")){
                makeDoctor();
            }else if( choice.equalsIgnoreCase("7")){
                makeNurse();
            }else if( choice.equalsIgnoreCase("8")){
                makeInsurance();
            }else if( choice.equalsIgnoreCase("4")){
                getPatientHistory();
            }else if( choice.equalsIgnoreCase("5")){
                getPatientInfo();
            }else if( choice.equalsIgnoreCase("9")){
                saveState();
                break;
            }else{
                System.out.println("Invalid input, restart");
            }
        }
        System.out.println("Thank you for using our Hospital Management System");
    }
}