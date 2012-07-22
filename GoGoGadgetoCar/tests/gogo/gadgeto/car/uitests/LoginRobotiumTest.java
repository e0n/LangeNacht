package gogo.gadgeto.car.uitests;

import gogo.gadgeto.car.LoginActivity;
import gogo.gadgeto.car.helper.DatabaseHandler;
import gogo.gadgeto.car.helper.UserFunctions;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;

import com.jayway.android.robotium.solo.Solo;


public class LoginRobotiumTest extends
		ActivityInstrumentationTestCase2<LoginActivity> {

	private Solo solo;
	public LoginRobotiumTest () {
		super(LoginActivity.class);
	}
	
	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		
	}
	
	@Smoke
	public void testLoginAndLogOut() throws Exception {
		// TODO don't use dbh.getRowCount() to proof correctness, OR test it before!
		// tests the login by entering username and password and click "Log In"
		Context context = solo.getCurrentActivity().getApplicationContext();
		solo.enterText(0, "testuser@test.de");
		solo.enterText(1, "test");
		solo.clickOnButton("Log In");
		//have to wait on the data from the server which allow us to enter or deny.
		solo.waitForActivity("MainMenuActivity");
		boolean expected = true;
		boolean actual = new UserFunctions().isUserLoggedIn(context);

		// Assert that our hidden button is no longer hidden
		assertEquals("User should be logged in!", expected, actual);
		
		// Logout the User
		context = solo.getCurrentActivity().getApplicationContext();
		new UserFunctions().logoutUser(context);
		DatabaseHandler dbh = new DatabaseHandler(context);
		assertEquals("There should be no rows left in the database...",0, dbh.getRowCount());
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
