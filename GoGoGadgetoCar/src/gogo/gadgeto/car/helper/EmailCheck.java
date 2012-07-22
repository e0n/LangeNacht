package gogo.gadgeto.car.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailCheck {
	
	public boolean isEmailAdress(String emailString) {
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(emailString);
		boolean matchFound = m.matches();
		return matchFound;
	} 
}
