package gogo.gadgeto.car.userfunctions.tests;

import java.util.HashMap;

import gogo.gadgeto.car.LoginActivity;
import gogo.gadgeto.car.helper.DatabaseHandler;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.Smoke;
import android.util.Log;

import com.jayway.android.robotium.solo.Solo;


public class DatabaseHandlerTest extends
		ActivityInstrumentationTestCase2<LoginActivity> {

	private Solo solo;
	private DatabaseHandler dbh;
	private SQLiteDatabase ldb;
	
	public DatabaseHandlerTest () {
		super(LoginActivity.class);
	}
	

	public void setUp() {
		// new Solo instance which represents the activity
		solo = new Solo(getInstrumentation(), getActivity());
		//get the Context
		Context context = solo.getCurrentActivity().getApplicationContext();
		//init the DatabaseHandler and open a writeable database
		dbh = new DatabaseHandler(context);
		ldb = dbh.getWritableDatabase();
	}
	
	@Smoke
	public void testDatabaseHandler() {
		//Check if the "new DatabaseHandler()" function worked correctly and opened a database
		boolean actual = ldb.isOpen();
		assertEquals(true, actual);
	}
	
	@Smoke
	public void testOnCreateTableLoginExist() {
		//Select the login table
		Cursor actual = ldb.rawQuery("select * from login;",null);
		assertEquals(true, actual != null);
	}
	
	@Smoke
	public void testAddUser() {
		// Function which have to be tested
		dbh.addUser("Tobi", "jan_delay688@web.de", "1", "Lalalala");
		boolean correct = false;
		// get a "new" writeable database Connection
		ldb = dbh.getWritableDatabase();
		// database request to find the new added User
		Cursor actual = ldb.rawQuery("select * from login where email='jan_delay688@web.de';", null);
		// set the cursor to the first row of the result table
		actual.moveToFirst();
		//if the requested table is empty, avoid data access
		if(actual.getCount() > 0){
			Log.e("DB-Ausgabe", actual.getString(1));
			correct = actual.getString(1).equalsIgnoreCase("jan_delay688@web.de");
		}
		assertEquals("User is already registered in Android Database",true, correct);
	}
	
	@Smoke
	public void testJoinCarSharing() {
		//Add User do let him join a cargroup
		dbh.addUser("Tobi", "jan_delay688@web.de", "1", "Lalalala");
		// Function which have to be tested
		dbh.joinCarGroup("23");
		boolean correct = false;
		// get a "new" writeable database Connection
		ldb = dbh.getWritableDatabase();
		// database request to find the new added User
		Cursor actual = ldb.rawQuery("select * from login;",null);
		// set the cursor to the first row of the result table
		actual.moveToFirst();
		//if the requested table is empty, avoid data access
		if(actual.getCount() > 0){
			Log.e("DB-Ausgabe", actual.getString(2));
			correct = actual.getString(2).equalsIgnoreCase("23");
		}
		assertEquals("Carsharing is already registered in Android Database",true, correct);
	}
	
	@Smoke
	public void testGetUserDetails() {
		//Add User to get his details
		dbh.addUser("Tobi", "jan_delay688@web.de", "1", "20.01.2012");
		// Function which have to be tested
		HashMap<String,String> user = dbh.getUserDetails();
		boolean correct = false;
		if(user.get("name").equalsIgnoreCase("Tobi") && 
				user.get("email").equalsIgnoreCase("jan_delay688@web.de")&&
				user.get("cargroupid").equalsIgnoreCase("1")&&
				user.get("created_at").equalsIgnoreCase("20.01.2012")){
			correct = true;
		}
		// get a "new" writeable database Connection
		ldb = dbh.getWritableDatabase();
		assertEquals("Received User Data doesn't match the created User",true, correct);
	}
	
	@Smoke
	public void testGetRowCount() {
		// Add one User to test the count of rows of the database
		dbh.addUser("User1", "user@userhost.de","1", "20.01.2012");
		//get Rows
		int count = dbh.getRowCount();
		//open Database for teardown
		ldb = dbh.getWritableDatabase();
		assertEquals("Database should only have one entry...", 1, count );
	}
	
	@Smoke
	public void testDeleteUser() {
		//Add a User and check that there is one User in it
		dbh.addUser("User1", "user@userhost.de", "1", "20.01.2012");
		assertEquals("Error: There should be only 1 User in the Database", 1, dbh.getRowCount());
		// Call resetlogin to clear the database
		dbh.deleteUser();
		assertEquals("There should be no User in the database", 0, dbh.getRowCount());
		// Open writable database
		ldb = dbh.getWritableDatabase();
	}
	
	@Override
	public void tearDown() throws Exception {
		//close the database connection
		ldb.delete("login", null, null);
		ldb.close();
		//finished all opened Activities
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