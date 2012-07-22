package gogo.gadgeto.car.uitests;

import gogo.gadgeto.car.LoginActivity;
import gogo.gadgeto.car.helper.UserFunctions;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;

import com.jayway.android.robotium.solo.Solo;


public class RegisterTest extends
		ActivityInstrumentationTestCase2<LoginActivity> {

	private Solo solo;
	public RegisterTest () {
		super(LoginActivity.class);
	}
	
	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		
	}

	@Smoke
	public void testRegister() throws Exception {
		// this tests the registration of a new User
		solo.clickOnButton("Register");
		solo.waitForActivity("RegisterActivity");
        solo.assertCurrentActivity("Expect RegisterActivity shown...", "RegisterActivity");
        
		solo.enterText(0, "to ladewi");
		solo.enterText(1, "123");
		solo.enterText(2, "toladewi@htwg-konstanz.de");
		solo.clickOnButton("Register");
		
		Thread.sleep(10000);
		solo.assertCurrentActivity("Expect MainMenuActivity", "MainMenuActivity");
		
		new UserFunctions().logoutUser(solo.getCurrentActivity().getApplicationContext());
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