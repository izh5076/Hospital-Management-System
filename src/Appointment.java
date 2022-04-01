
import java.util.Date;

/**
 * Appointment.java:
 *
 * Class to  model appointments and stores dates and times
 *
 * @author Issac Heim
 * @author Christopher Brennan
 */

public class Appointment
{

// Instance variables
    private Date date;
    private String time;
    private Patient patient;
    private Doctor doctor;


    public Appointment()
    {
        this.date = null;
    }
    public Appointment(Date date1, String time1) throws InvalidDateException
    {
        this.setAppointmentDate(date1);
        this.setAppointmentTime(time1);
    }
    public Appointment(int month, int year, int day, String time1) throws InvalidDateException
    {
        date = new Date(year, month, day);
        time = time1;
    }

    public void setAppointmentDate(Date date1) throws InvalidDateException
    {
        date = date1;
    }

    public void setAppointmentTime(String time1)
    {
        this.time = time1;
    }

    public Date getAppointmentDate()
    {
        return this.date;
    }

    public String getAppointmentTime()
    {
        return this.time;
    }




}
