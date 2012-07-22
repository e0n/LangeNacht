package gogo.gadgeto.car.userfunctions.tests;

import gogo.gadgeto.car.LoginActivity;
import gogo.gadgeto.car.helper.UserFunctions;

import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;
import android.util.Log;

import com.jayway.android.robotium.solo.Solo;


public class UserFunctionsRegistrationTest extends
		ActivityInstrumentationTestCase2<LoginActivity> {

	private Solo solo;
	private UserFunctions userFunc;
	
	public UserFunctionsRegistrationTest () {
		super(LoginActivity.class);
	}
	

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		userFunc = new UserFunctions();
	}
		
	@Smoke
	public void testBRegisterUser(){
		//Create a JSon Object which contains the return value of registerUser()
		JSONObject json = userFunc.registerUser("Dummy User", "dummyuser2@test.de", "dummy");
		//convert to String to compare it
		String actual = json.toString();
		Log.e("json.tostring", actual);
		boolean correct = false;
		//CHeck start and End of String
		if(actual.startsWith("{\"user\":{\"cargroupid\":") && actual.endsWith("tag\":\"registerUser\"}")){
			correct = true;
		}
		assertTrue("User already in Database - delete it manualy", correct);
		userFunc.logoutUser(solo.getCurrentActivity().getApplicationContext());
	}
	
	@Smoke
	public void testARegisterUserAlreadyExist(){
		//Create a JSon Object which contains the return value of registerUser()
		JSONObject json = userFunc.registerUser("Test User", "testuser@test.de", "test");
		//convert to String to compare it
		String actual = json.toString();
		Log.e("json.tostring", actual);
		//the expected String
		String expected = "{\"error\":2,\"success\":0,\"error_msg\":\"User already exists!\",\"tag\":\"registerUser\"}";
		assertTrue("User should already exist in db", actual.equalsIgnoreCase(expected));
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
