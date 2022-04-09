package Appointment;

import java.util.Date;

public class InvalidDateException  extends Exception
{
    public InvalidDateException(int time)
    {
        super("The time " + time + "is outside of our office's hours");
    }

    public InvalidDateException(int year, int month, int day)
    {
        super(day + "/" + month + "/" + year + "does not exist, or is outside of operating dates");
    }


}
