package gogo.gadgeto.car.userfunctions.tests;

import gogo.gadgeto.car.LoginActivity;
import gogo.gadgeto.car.helper.UserFunctions;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;
import android.util.Log;

import com.jayway.android.robotium.solo.Solo;


public class UserFunctionsLogingAndGetterTest extends
		ActivityInstrumentationTestCase2<LoginActivity> {

	private Solo solo;
	private UserFunctions userFunc;
	
	public UserFunctionsLogingAndGetterTest () {
		super(LoginActivity.class);
	}
	

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		userFunc = new UserFunctions();
		//Login a User
		userFunc.loginUser("testuser@test.de", "test");
		Thread.sleep(10000);
	}
	
	
	@Smoke
	public void testLogOutUser(){
		boolean logout = userFunc.logoutUser(solo.getCurrentActivity().getApplicationContext());
		String hallo = "Nein";
		if(logout){
			hallo = "yes";
		}
		Log.e("LOgged In", hallo);
		assertTrue("User should be Logged out...", logout);
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
