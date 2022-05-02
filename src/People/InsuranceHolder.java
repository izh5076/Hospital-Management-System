package People;

import java.util.ArrayList;

/**
 * InsuranceHolder.java:
 * interface for when people need to hold insurance info
 *
 * @author Christopher Brennen
 * @author Issac Heim
 */
public interface InsuranceHolder {
     void setInsuranceType( ArrayList<String> type );
     ArrayList<String> getInsuranceType();
}
