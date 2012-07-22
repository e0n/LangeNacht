package gogo.gadgeto.car.userfunctions.tests;

import gogo.gadgeto.car.LoginActivity;
import gogo.gadgeto.car.helper.UserFunctions;

import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;
import android.util.Log;

import com.jayway.android.robotium.solo.Solo;


public class UserFunctionsTest extends
		ActivityInstrumentationTestCase2<LoginActivity> {

	private Solo solo;
	private UserFunctions userFunc;
	
	public UserFunctionsTest () {
		super(LoginActivity.class);
	}
	

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		userFunc = new UserFunctions();
	}
	
	@Smoke
	public void testLoginUser() throws Exception {
		JSONObject json = userFunc.loginUser("testuser@test.de", "test");
		String actual = json.toString();
		Log.e("json.tostring", actual);
		String expected = "{\"user\":{\"cargroupid\":null,\"created_at\":\"2012-07-22 15:42:29\",\"email\":\"testuser@test.de\",\"name\":\"Test User\"},\"success\":1,\"tag\":\"loginUser\"}";
		assertTrue("String is not equal...", actual.equalsIgnoreCase(expected));
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
