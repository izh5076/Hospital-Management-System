import java.util.ArrayList;

public class Schedule {
    ArrayList<Date> schedule = new ArrayList<Date>();
    public Schedule(){}
    public void add( Date date ){
        schedule.add( date );
    }
    public void displaySchedule(){
        System.out.println("The schedule is as follows:");
        for (Date d : schedule) {
            System.out.println(d.toString());
        }
    }
}
