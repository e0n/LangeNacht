package gogo.gadgeto.car.userfunctions.tests;

import gogo.gadgeto.car.LoginActivity;
import gogo.gadgeto.car.helper.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;
import android.util.Log;

import com.jayway.android.robotium.solo.Solo;


public class UserFunctionsCarGroupTest extends
		ActivityInstrumentationTestCase2<LoginActivity> {

	private Solo solo;
	private UserFunctions userFunc;
	
	public UserFunctionsCarGroupTest () {
		super(LoginActivity.class);
	}
	

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		userFunc = new UserFunctions();
		userFunc.loginUser("testuser@test.de", "test");
	}
	
	@Smoke
	public void testARegisterCarGroup(){
		//Create a JSon Object which contains the return value of registerCarGroup()
		JSONObject json = userFunc.registerCarGroup("1234");
		//convert to String to compare it
		String actual = json.toString();
		Log.e("json.tostring", actual);
		//compare beginning and end of the String, because we don't know the carGroupId we got
		boolean correct = false;
		if(actual.startsWith("{\"cargroupid\":") && 
				actual.endsWith("\"success\":1,\"tag\":\"registerCarGroup\"}")){
			correct = true;
		}
		assertTrue("Registration should succeed...", correct);
	}
	
	@Smoke
	public void testJoinCarGroup(){
		String password = "1234";
		//Create a JSon Object to get the CarId
		JSONObject carGrpJason = userFunc.registerCarGroup(password);
		String carId = "";
		try {
			carId = carGrpJason.getString("cargroupid");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		//Create a JSon Object which contains the return value of joinCarGroup()
		JSONObject json = userFunc.joinCarGroup(solo.getCurrentActivity().getApplicationContext(), carId, password);
		//convert to String to compare it
		String actual = json.toString();
		Log.e("json.toString", actual);
		String expected = "{\"success\":1,\"tag\":\"joinCarGroup\"}";
		assertTrue("Registration should succeed...", expected.equalsIgnoreCase(actual));
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
