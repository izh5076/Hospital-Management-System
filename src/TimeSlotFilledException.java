import java.util.Date;

public class TimeSlotFilledException extends Exception{
    Date date;
    String time;
    public TimeSlotFilledException( Date date, String time ){
        super("The time slot you are trying to schedule is already full: " + date.toString() + " " + time + ":00.");
        this.date = date;
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

}
