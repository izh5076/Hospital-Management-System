package Time;

import java.util.Date;

/**
 * TimeSlotFilledException.java:
 * an exception for when the time is already booked
 *
 * @author Christopher Brennen
 * @author Issac Heim
 */
public class TimeSlotFilledException extends Exception{
    Date date;
    int time;
    public TimeSlotFilledException( Date date, int time ){
        super("The time slot you are trying to schedule is already full: " + date.toString() + " " + time + ":00.");
        this.date = date;
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }

}
