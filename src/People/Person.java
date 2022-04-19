package People;

/**
 * Person.java:
 * this is an abstract class holding basic information for people
 *
 * @author Christopher Brennen
 * @author Issac Heim
 */
public abstract class Person
{
    //instance variables
    private String name;
    private String gender;


    public Person()
    {
        this.name = null;
        this.gender = null;
    }

    /**
     * Two parameter constructor to set the name and gender of the person.
     * @param name1 name of the person
     * @param gender1 gender of the person
     */
    public Person(String name1, String gender1)
    {
        this.name = name1;
        this.gender = gender1;
    }

}
