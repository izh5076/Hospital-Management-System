package Time;

import Time.Appointment.Appointment;

import java.util.ArrayList;

/**
 * Schedule.java:
 * this is a schedule object, basically an arraylist of appoitnments with some logic
 *
 * @author Christopher Brennen
 * @author Issac Heim
 */
public class Schedule {
    ArrayList<Appointment> schedule = new ArrayList<Appointment>();

    public Schedule(){}

    /**
     * simply adds an appointment to the schedule, checks if the slot is clear
     *
     * @param apt
     * @throws TimeSlotFilledException
     */
    public void add( Appointment apt ) throws TimeSlotFilledException {
        for ( Appointment a : schedule ) {
            if( apt.getAppointmentDate().equals( a.getAppointmentDate() ) && ( apt.getAppointmentTime() == a.getAppointmentTime() ) ){
                throw new TimeSlotFilledException( apt.getAppointmentDate(), apt.getAppointmentTime() );
            }
        }

        // This bit should sort the new appointment into chronological order in the schedule
        int i = 0;

        // This assumes that compareTo works for Date objects like I think they do, positive being after the compared date
        while( apt.getAppointmentDate().compareTo( schedule.get(i).getAppointmentDate() ) > 0 ){
            i++;
        }
        while( apt.getAppointmentTime() > schedule.get(i).getAppointmentTime() ) {
            i++;
        }
        schedule.add( i, apt );
    }

    /**
     * prints the schedule to output stream
     */
    public void displaySchedule(){
        System.out.println("The schedule is as follows:");
        for (Appointment a : schedule) {
            System.out.println(a.toString());
        }
    }
}
