package gogo.gadgeto.car.uitests;

import gogo.gadgeto.car.CreateCarGroupActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;

import com.jayway.android.robotium.solo.Solo;


public class CreateCarGrpTest extends
		ActivityInstrumentationTestCase2<CreateCarGroupActivity> {

	private Solo solo;
	public CreateCarGrpTest () {
		super(CreateCarGroupActivity.class);
	}
	

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	@Smoke
	public void testCreateGrp() throws Exception {
		//New password
		solo.enterText(0, "1234");
		//repeat New password
		solo.enterText(1, "1234");
		solo.enterText(2, "52390");
		solo.clickOnButton("Create group");
		
		
		//have to wait on the data from the server which allow us to enter or deny.
		solo.waitForActivity("MainMenuActivity",10000);
		solo.assertCurrentActivity("Expect MainMenuActivity....", "MainMenuActivity");
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
