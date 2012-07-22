package gogo.gadgeto.car.uitests;

import gogo.gadgeto.car.MainMenuActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;

import com.jayway.android.robotium.solo.Solo;


public class DistanceTest extends
		ActivityInstrumentationTestCase2<MainMenuActivity> {

	private Solo solo;
	public DistanceTest () {
		super(MainMenuActivity.class);
	}
	
	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());			
	}
	
	@Smoke
	public void testEnterNewDistance() {
		//Enter new Mileage in kilometers
		solo.enterText(0, "450");
		solo.clickOnButton("Send");
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
