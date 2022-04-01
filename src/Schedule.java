import java.util.ArrayList;

public class Schedule {
    ArrayList<Appointment> schedule = new ArrayList<Appointment>();

    public Schedule(){}

    public void add( Appointment apt ) throws TimeSlotFilledException{
        for ( Appointment a : schedule ) {
            if( apt.getDate().equals( a.getDate() ) && apt.getTime().equals( a.getTime() ) ){
                throw new TimeSlotFilledException( apt.getDate(), apt.getTime() );
            }
        }

        schedule.add( apt );
    }

    public void displaySchedule(){
        System.out.println("The schedule is as follows:");
        for (Appointment a : schedule) {
            System.out.println(a.toString());
        }
    }
}
