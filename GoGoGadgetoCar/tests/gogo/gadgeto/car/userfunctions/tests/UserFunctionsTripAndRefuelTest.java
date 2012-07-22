package gogo.gadgeto.car.userfunctions.tests;

import gogo.gadgeto.car.LoginActivity;
import gogo.gadgeto.car.helper.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;
import android.util.Log;

import com.jayway.android.robotium.solo.Solo;


public class UserFunctionsTripAndRefuelTest extends
		ActivityInstrumentationTestCase2<LoginActivity> {

	private Solo solo;
	private UserFunctions userFunc;
	
	public UserFunctionsTripAndRefuelTest () {
		super(LoginActivity.class);
	}
	

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		userFunc = new UserFunctions();
		userFunc.loginUser("testuser@test.de", "test");
		userFunc.leaveCarGroup(solo.getCurrentActivity().getApplicationContext());
		userFunc.registerCarGroup("1234");
	}
	
	@Smoke
	public void testAddTrip(){
		//Create a JSon Object which contains the return value of addTrip()
		JSONObject json = userFunc.addTrip(solo.getCurrentActivity().getApplicationContext(), "200");
		//convert to String to compare it
		String actual = json.toString();
		Log.e("json.TRIP", actual);
		// TODO Tobi Expected String 
		boolean correct = false;
		if(actual.startsWith("{\"cargroupid\"") && 
				actual.endsWith("\"success\":1,\"tag\":\"registerCarGroup\"}")){
			correct = true;
		}
		assertTrue("Trip should be added...", correct);
	}

	@Smoke
	public void testAddRefuel(){
		//Create a JSon Object which contains the return value of addRefuel()
		JSONObject json = userFunc.addRefuel(solo.getCurrentActivity().getApplicationContext(), "1200", "85");
		//convert to String to compare it
		String actual = json.toString();
		Log.e("json.REFUEL", actual);
		// TODO Tobi Expected String 
		boolean correct = false;
		if(actual.startsWith("{\"cargroupid\"") && 
				actual.endsWith("\"success\":1,\"tag\":\"registerCarGroup\"}")){
			correct = true;
		}
		assertTrue("Refuel should be added...", correct);
	}
	
	@Smoke
	public void testGetUserStatistics(){
		//Create a JSon Object which contains the return value of addRefuel()
		JSONObject refuel = userFunc.addRefuel(solo.getCurrentActivity().getApplicationContext(), "1200", "85");
		String refuelId = "";
		try {
			refuelId = refuel.getString("refuelId");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json = userFunc.getUserStatistics(refuelId);
		//convert to String to compare it
		String actual = json.toString();
		Log.e("json.USERSTAT", actual);
		boolean correct = false;
		if(actual.startsWith("{\"cargroupid\"") && 
				actual.endsWith("\"success\":1,\"tag\":\"registerCarGroup\"}")){
			correct = true;
		}
		assertTrue("User Statistics should be returned...", correct);
	}
	
	//TODO Wenn es nicht läuft muss es raus
//	@Smoke
//	public void testGetRefuelings(){
//		userFunc.addRefuel(solo.getCurrentActivity().getApplicationContext(), "50", "250");
//		//Create a JSon Object which contains the return value of getRefuelings()
//		JSONObject json = userFunc.getRefuelings(solo.getCurrentActivity().getApplicationContext());
//		//convert to String to compare it
//		String actual = json.toString();
//		Log.e("json.GETREFUEL", actual);
//		// TODO Tobi String expected
//		String expected = "";
//		assertTrue("Refuelings should be returned...", actual.equalsIgnoreCase(expected));
//	}
	
	@Smoke
	public void testGetRefuelingsFailed(){
		//Create a JSon Object which contains the return value of getRefuelings()
		JSONObject json = userFunc.getRefuelings(solo.getCurrentActivity().getApplicationContext());
		//convert to String to compare it
		String actual = json.toString();
		Log.e("json.GETREFUEL", actual);
		// TODO Tobi String expected
		String expected = "{\"success\":0,\"error_msg\":\"Couldn't find any refueling\",\"tag\":\"getRefuelings\"}";
		assertTrue("Refuelings should be returned...", actual.equalsIgnoreCase(expected));
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
