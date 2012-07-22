package gogo.gadgeto.car.uitests;
//package gogo.gadgeto.car.test;
//
//import gogo.gadgeto.car.MainMenuActivity;
//import gogo.gadgeto.car.R;
//import gogo.gadgeto.car.helper.UserFunctions;
//import gogo.gadgeto.model.Database;
//import android.annotation.TargetApi;
//import android.content.Context;
//import android.test.ActivityInstrumentationTestCase2;
//import android.test.suitebuilder.annotation.Smoke;
//
//import com.jayway.android.robotium.solo.Solo;
//
//
//public class FuelFragmentTest extends
//		ActivityInstrumentationTestCase2<MainMenuActivity> {
//
//	private Solo solo;
//	public FuelFragmentTest () {
//		super(MainMenuActivity.class);
//	}
//	
//	@TargetApi(11)
//	public void setUp() throws Exception {
//		solo = new Solo(getInstrumentation(), getActivity());
//		solo.getCurrentActivity().getFragmentManager().findFragmentById(R.layout.fragment_fuel);
//		
//	}
//	
//	@Smoke
//	public void testFuel() throws Exception {
//		//Lars
//		solo.pressSpinnerItem(0, 0);
//		//Amount (€)
//		solo.enterText(0, "70");
//		//Mileage
//		solo.enterText(1, "1200");
//		solo.clickOnButton("Send");
//		
//		
//		//have to wait on the data from the server which allow us to enter or deny.
//		solo.waitForActivity("MainMenuActivity");
//		String expected = "Hallo";
//		String actual = Database.getInstance().sendFuelToDatabase("Lars", 70, 1200, "");
//		// Assert that our hidden button is no longer hidden
//		assertEquals("User should be logged in!", expected, actual);
//		
//		new UserFunctions().logoutUser(solo.getCurrentActivity().getApplicationContext());
//	}
//	
//	@Override
//	public void tearDown() throws Exception {
//		solo.finishOpenedActivities();
//		try {
//			// Robotium will finish all the activities that have been opened	
//			solo.finalize();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		getActivity().finish();
//		super.tearDown();
//	}
//}
