package Time.Appointment;


/**
 * InvalidDateException.java:
 *
 * Exception class that is thrown when there is an invalid date entered or used as a parameter
 *
 * @author Issac Heim
 * @author Christopher Brennen
 */
public class InvalidDateException extends Exception
{
    /**
     * Constructor to be used when there is an invalid time entered
     *
     * @param time The invalid time
     */
    public InvalidDateException(int time)
    {
        super("The time " + time + " is outside of our office's hours");
    }

    /**
     * Constructor to be used when there is an invalid date entered
     *
     * @param year The year of the invalid date
     * @param month The month of the invalid date
     * @param day The day of the invalid date.
     */
    public InvalidDateException(int year, int month, int day)
    {
        super(month + "/" + day  + "/" + year + " does not exist, or is outside of operating dates");
    }


}
