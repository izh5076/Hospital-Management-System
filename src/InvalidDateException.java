public class InvalidDateException  extends Exception
{
    public InvalidDateException()
    {
        super("The date entered is outside of hospital hours. ");
    }
}
