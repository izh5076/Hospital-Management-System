package Time;

import java.util.HashMap;

/**
 * MonthDays.java:
 * this class holds the number of days in each month via hashmap
 *
 * @author Christopher Brennen
 * @author Issac Heim
 */
public class MonthDays {
    // static variable
    private static HashMap<Integer, Integer> table = new HashMap<>();
    //static values
    static {
        table.put(1,31);
        table.put(2,28);
        table.put(3,31);
        table.put(4,30);
        table.put(5,31);
        table.put(6,30);
        table.put(7,31);
        table.put(8,31);
        table.put(9,30);
        table.put(10,31);
        table.put(11,30);
        table.put(12,31);
    }

    /**
     * get method for returning the value from table
     *
     * @param month
     * @return
     */
    public static Integer getDays( int month ){
        return table.get(month);
    }

    /**
     * quick testing main
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(MonthDays.getDays(4));
    }
}
