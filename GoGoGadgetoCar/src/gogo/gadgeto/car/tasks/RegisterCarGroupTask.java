package gogo.gadgeto.car.tasks;

import gogo.gadgeto.car.CreateCarGroupActivity;
import gogo.gadgeto.car.helper.DatabaseHandler;
import gogo.gadgeto.car.helper.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class RegisterCarGroupTask extends AsyncTask<Void, Void, Void> {

    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR_MSG = "error_msg";
    private static final String KEY_CARGROUPID = "cargroupid";
    
    // Properties
    private CreateCarGroupActivity activity;
    private ProgressDialog mProgressDialog;
    private UserFunctions userFunction;
    private JSONObject json;
    private String password;
    private String error_msg;
	
	public RegisterCarGroupTask(CreateCarGroupActivity activity, String password)
	{
		this.activity = activity;
		this.password = password;
		this.error_msg = "";
	}
	
    @Override
	protected void onPostExecute(Void result) {
    	mProgressDialog.dismiss(); 
    	if (activity != null) {
    		if (!error_msg.equals("")) {
    			activity.showError(error_msg);
    		}
    		else {
        		activity.carGroupWindow();
    		}
    	}	
	}
    
    @Override
    protected void onPreExecute() {
        mProgressDialog = ProgressDialog.show(activity, "Loading...", "Fetching data from server...");
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
	                String carGroupId = json.getString(KEY_CARGROUPID);
	                db.joinCarGroup(carGroupId); 
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