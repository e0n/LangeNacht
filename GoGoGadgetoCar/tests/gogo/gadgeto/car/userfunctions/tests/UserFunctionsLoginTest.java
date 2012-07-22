package gogo.gadgeto.car.userfunctions.tests;

import gogo.gadgeto.car.LoginActivity;
import gogo.gadgeto.car.helper.UserFunctions;

import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;
import android.util.Log;

import com.jayway.android.robotium.solo.Solo;


public class UserFunctionsLoginTest extends
		ActivityInstrumentationTestCase2<LoginActivity> {

	private Solo solo;
	private UserFunctions userFunc;
	
	public UserFunctionsLoginTest () {
		super(LoginActivity.class);
	}
	

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		userFunc = new UserFunctions();
	}
	
	@Smoke
	public void testBLoginUser() throws Exception {
		//Create a JSon Object which contains the return value of loginUser()
		JSONObject json = userFunc.loginUser("testuser@test.de", "test");
		//convert to String to compare it
		String actual = json.toString();
		Log.e("json.tostring", actual);
		//the expected String
		boolean correct = false;
		if(actual.startsWith("{\"user\":{\"cargroupid\"") && 
				actual.endsWith("success\":1,\"tag\":\"loginUser\"}")){
			correct = true;
		}
		assertTrue("Login should happen...", correct);
		userFunc.logoutUser(solo.getCurrentActivity().getApplicationContext());
	}
	
	@Smoke
	public void testALoginUserFailed() throws Exception {
		//Create a JSon Object which contains the return value of loginUser()
		JSONObject json = userFunc.loginUser("testuser@test.de", "1234");
		//convert to String to compare it
		String actual = json.toString();
		Log.e("json.tostring", actual);
		//the expected String
		String expected = "{\"success\":0,\"error_msg\":\"Incorrect email or password!\",\"tag\":\"loginUser\"}";
		assertTrue("Login should not happen...", actual.equalsIgnoreCase(expected));
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
