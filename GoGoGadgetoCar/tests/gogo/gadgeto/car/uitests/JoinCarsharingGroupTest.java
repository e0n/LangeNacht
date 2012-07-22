package gogo.gadgeto.car.uitests;

import gogo.gadgeto.car.JoinCarGroupActivity;
import gogo.gadgeto.car.helper.UserFunctions;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;

import com.jayway.android.robotium.solo.Solo;


public class JoinCarsharingGroupTest extends
		ActivityInstrumentationTestCase2<JoinCarGroupActivity> {

	private Solo solo;
	public JoinCarsharingGroupTest () {
		super(JoinCarGroupActivity.class);
	}
	
	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());	
	}
	
	@Smoke
	public void testJoinCarsharingGroup() {
		//Testing the join of an existing CarSharingGroup
		//CarGrp-ID
		solo.enterText(0, "11");
		//Password
		solo.enterText(1, "12");
		solo.clickOnButton("Send");
		//have to wait on the data from the server which allow us to enter or deny.
		solo.waitForActivity("MainMenuActivity",10000);
		solo.assertCurrentActivity("Expect MainMenuActivity....", "MainMenuActivity");
	}
	
	@Smoke
	public void testJoinCarsharingGroupFailed() {
		//Testing the join of an existing CarSharingGroup
		//CarGrp-ID
		solo.enterText(0, "11");
		//Password
		solo.enterText(1, "FalschesPasswort");
		solo.clickOnButton("Send");
		//have to wait on the data from the server which allow us to enter or deny.
		solo.waitForActivity("JoinCarGroupActivity",10000);
		solo.assertCurrentActivity("Expect JoinCarGroupActivity....", "JoinCarGroupActivity");
	}
	
	@Override
	public void tearDown() throws Exception {
		new UserFunctions().logoutUser(solo.getCurrentActivity().getApplicationContext());
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
