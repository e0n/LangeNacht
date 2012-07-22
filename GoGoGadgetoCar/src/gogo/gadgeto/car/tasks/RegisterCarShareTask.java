package gogo.gadgeto.car.tasks;

import gogo.gadgeto.car.CreateCarGroupActivity;
import gogo.gadgeto.car.helper.DatabaseHandler;
import gogo.gadgeto.car.helper.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class RegisterCarShareTask extends AsyncTask<Void, Void, Void> {

    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR_MSG = "error_msg";
    private static final String KEY_CARGROUPID = "cargroupid";
    
    // Properties
    private CreateCarGroupActivity activity;
    private UserFunctions userFunction;
    private JSONObject json;
    private String password;
    private String error_msg;
	
	public RegisterCarShareTask(CreateCarGroupActivity activity, String password, String mileage)
	{
		this.activity = activity;
		this.password = password;
		this.error_msg = "";
	}
	
    @Override
	protected void onPostExecute(Void result) {
    	activity.showError(error_msg);
	}

    @Override
    protected Void doInBackground(Void... params) {        
        userFunction = new UserFunctions();
        json = userFunction.registerCarGroup(password);
		
		try {
            if (json.getString(KEY_SUCCESS) != null) {
                String res = json.getString(KEY_SUCCESS);
                if(Integer.parseInt(res) == 1){
                	
	                // registration successfully
	                // Store cargroupid in SQLite Database
	                DatabaseHandler db = new DatabaseHandler(activity.getApplicationContext());                							
	                String carshareId = json.getString(KEY_CARGROUPID);
	                db.updateCarGroup(carshareId);
	                
	                error_msg = carshareId;
                }
            }
			else {
				error_msg = json.getString(KEY_ERROR_MSG);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
        
        return null;
    }
}