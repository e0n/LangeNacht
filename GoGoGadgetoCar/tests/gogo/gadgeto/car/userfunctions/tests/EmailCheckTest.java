package gogo.gadgeto.car.userfunctions.tests;

import gogo.gadgeto.car.LoginActivity;
import gogo.gadgeto.car.helper.EmailCheck;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;

public class EmailCheckTest extends
		ActivityInstrumentationTestCase2<LoginActivity> {

	EmailCheck ec = new EmailCheck();
	String mail = new String();
	
	public EmailCheckTest () {
		super(LoginActivity.class);
	}
	
	public void setUp() throws Exception {
		mail = "";
	}
	
	@Smoke
	public void testCorrectEmail() {
		//Enter a correct Email adress
		mail = "toladewig@gmail.com";
		assertEquals("Mail adress is correct and should be true",true, ec.isEmailAdress(mail));
	}
	
	@Smoke
	public void testIncorrectEmail() {
		//Enter a correct Email adress
		mail = "gmail@gmail";
		assertEquals("Mail adress is correct and should be false",false, ec.isEmailAdress(mail));
	}
	
	@Smoke
	public void testIncorrectEmailWithNumbersAtTheEnd() {
		//Enter a correct Email adress
		mail = "toaldewig@gmail.com678";
		assertEquals("Mail adress is correct and should be false",false, ec.isEmailAdress(mail));
	}
	
	@Smoke
	public void testIncorrectEmailWithoutAtSign() {
		//Enter a correct Email adress
		mail = "tobias.ladewiggmail.com";
		assertEquals("Mail adress is correct and should be false",false, ec.isEmailAdress(mail));
	}
	
	@Override
	public void tearDown() throws Exception {
		super.tearDown();
	}
}
