package Time.Appointment;

import People.Patient.Patient;
import Time.MonthDays;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * Time.Appointment.Time.Appointment.java:
 *
 * Class to  model appointments and stores dates and times
 *
 * @author Issac Heim
 * @author Christopher Brennen
 */

public class Appointment implements Serializable
{

    // Instance variables
    private Date date;
    private int startTime;
    private Patient patient;
    private LocalDate currentDate = LocalDate.now();
    private LocalTime currentTime = LocalTime.now();

    //all appointments last 30 minutes, or 0.5 hours.
    private final String duration = "45 minutes";

    //Hospital opening and closing hours
    private final double openingHour = 9.0;
    private final double closingHour = 17.0;
    //Note appointments run on the 24-hour time schedule, rather than A.M. P.M

    /**
     * Constructor to take in a date object and an integer representing the starting time of the appointment
     *
     * @param date1 The date of the appointment
     * @param startTime the starting time of the appointment
     * @throws InvalidDateException when the date or time is out of hospital hours
     */
    public Appointment(Date date1, int startTime) throws InvalidDateException
    {
        if(!validateDate(date1.getYear(), date1.getMonth(), date1.getDay()))
        {
            throw new InvalidDateException(date1.getYear(), date1.getMonth(), date1.getDay());
        }
        else if(!validateTime(this.startTime))
        {
            throw new InvalidDateException(this.startTime);
        }
        else
        {
            this.setAppointmentDate(date1);
            this.setAppointmentTime(startTime);
        }
    }

    /**
     * Constructor that takes in parts of a Date object and creates the date object to be stored in the appointment
     * object, along with the starting time of the appointment
     *
     * @param year the year the appointment is in
     * @param month the month the appointment is in
     * @param day the day the appointment is on
     * @param time1 the starting time of the appointment
     * @param patient the patient this appointment is assigned to
     * @throws InvalidDateException when a date or time is out of the range of the hospitals hours.
     */
    public Appointment(int day, int month, int year, int time1, Patient patient) throws InvalidDateException
    {
        if(!validateDate(year, month, day))
        {
            throw new InvalidDateException(year, month, day);
        }
        else if(!validateTime(time1))
        {
            throw new InvalidDateException(time1);
        }
        else
        {
            this.date = new Date(year, month, day);
            this.startTime = time1;
            this.date.setHours(time1);
            this.date.setYear(year-1900);
            this.patient = patient;
        }
    }

    /**
     * Method that sets just the date of the appointment
     * @param date1 the date of the new appointment
     * @throws InvalidDateException when the date is out of the hospitals hours
     */
    public void setAppointmentDate(Date date1) throws InvalidDateException
    {
        if(!validateDate(date1.getYear(), date1.getMonth(), date1.getDay()))
        {
            throw new InvalidDateException(date1.getYear(), date1.getMonth(), date1.getDay());
        }
        else
        {
            this.date = date1;
        }
    }

    /**
     * Method to set the starting time of the appointment.
     * @param time1 the starting time of the appointment
     * @throws InvalidDateException when the time is outside operating hours of the hospital
     */
    public void setAppointmentTime(int time1) throws InvalidDateException
    {
        if(!validateTime(time1))
        {
            throw new InvalidDateException(time1);
        }
        else
        {
            this.startTime = time1;
            date.setTime(time1);
        }
    }

    /**
     * Method to validate that appointment time is in the hospitals hours
     * @param year year of the appointment
     * @param month month of the appointment
     * @param day day of the appointment
     * @return true if date is within hours, otherwise false.
     */
    public boolean validateDate(int year, int month, int day)
    {
        if(year < currentDate.getYear() || month > 12 || month <= 0 || day <= 0 || day > 31 || (day > MonthDays.getDays(month)) )
        {
            return false;
        }
        Date temp = new Date(year, month, day);
        if(temp.getDay() == 0 || temp.getDay() == 6){
            return false;
        }
        return true;
    }

    /**
     * Method to validate the time of the appointment
     * @param time the time of the appointment
     * @return true if date is within hours, otherwise false
     */
    public boolean validateTime(int time)
    {
        return !(time < openingHour) && !(time > closingHour);
    }


    /**
     * Accessor to return the date of the appointment
     *
     * @return The date of the appointment
     */

    public Date getAppointmentDate()
    {
        return this.date;
    }

    /**
     * Accessor to return the time of the appointment
     * @return the starting time of the appointment
     */
    public int getAppointmentTime()
    {
        return this.startTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                " patient: " + patient +
                ", date: " + date +
        //        ", time: " + startTime +
                ", length: " + duration +
                " }";
    }

}
