package gogo.gadgeto.car.uitests;

import gogo.gadgeto.car.LoginActivity;
import gogo.gadgeto.car.helper.UserFunctions;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;

import com.jayway.android.robotium.solo.Solo;


public class LoginFailedTest extends
		ActivityInstrumentationTestCase2<LoginActivity> {

	private Solo solo;
	public LoginFailedTest () {
		super(LoginActivity.class);
	}
	
	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		
	}
	
	@Smoke
	public void testLoginFailed() throws Exception {
		
		// tests the login by entering username and password and click "Log In"
		Context context = solo.getCurrentActivity().getApplicationContext();
		solo.enterText(0, "testuser@test.de");
		solo.enterText(1, "123");
		solo.clickOnButton("Log In");
		//have to wait on the data from the server which allow us to enter or deny. 
		Thread.sleep(10000);
		boolean expected = false;
		boolean actual = new UserFunctions().isUserLoggedIn(context);

		// Assert that our hidden button is no longer hidden
		assertEquals("User shouldn't be logged in!", expected, actual);
	}
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
		try {
			// Robotium will finish all the activities that have been opened
			solo.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		getActivity().finish();
		super.tearDown();
	}
}