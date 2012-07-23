package gogo.gadgeto.car.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailCheck {
	
    /**
     * @function regular expression for email
     * @return true if expression fits, else false
     * */
	public boolean isEmailAdress(String emailString) {
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(emailString);
		boolean matchFound = m.matches();
		return matchFound;
	} 
}
