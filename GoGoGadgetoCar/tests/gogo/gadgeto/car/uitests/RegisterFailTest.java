package gogo.gadgeto.car.uitests;

import gogo.gadgeto.car.LoginActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;

import com.jayway.android.robotium.solo.Solo;


public class RegisterFailTest extends
		ActivityInstrumentationTestCase2<LoginActivity> {

	private Solo solo;
	public RegisterFailTest () {
		super(LoginActivity.class);
	}
	
	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		
	}

	@Smoke
	public void testRegisteredUserAlreadyExists() throws Exception {
		// Does the same as the testRegister(), but the expected Activity is different.
		solo.clickOnButton("Register");
		solo.waitForActivity("RegisterActivity");
        solo.assertCurrentActivity("Expect RegisterActivity shown...", "RegisterActivity");
        
		solo.enterText(0, "Tobias Ladewig");
		solo.enterText(1, "123");
		solo.enterText(2, "toladewig@gmail.com");
		solo.clickOnButton("Register");
		
		Thread.sleep(10000);
		solo.assertCurrentActivity("Expect RegisterActivity....", "RegisterActivity");
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