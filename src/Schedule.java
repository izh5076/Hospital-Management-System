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

        // This bit should sort the new appointment into chronological order in the schedule
        int i = 0;
        while( apt.getDate().getYear() > schedule.get(i).getDate().getYear() {
            i++;
        }
        while( apt.getDate().getMonth() > schedule.get(i).getDate().getMonth() {
            i++;
        }
        while( apt.getDate().getDay() > schedule.get(i).getDate().getDay() {
            i++;
        }
        while( apt.getTime() > schedule.get(i).getTime {
            i++;
        }
        schedule.add( i, apt );
    }

    public void displaySchedule(){
        System.out.println("The schedule is as follows:");
        for (Appointment a : schedule) {
            System.out.println(a.toString());
        }
    }
}
