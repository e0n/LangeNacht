package gogo.gadgeto.car.tasks;

import gogo.gadgeto.car.JoinCarGroupActivity;
import gogo.gadgeto.car.helper.DatabaseHandler;
import gogo.gadgeto.car.helper.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class JoinCarGroupTask extends AsyncTask<Void, Void, Void> {

    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR_MSG = "error_msg";
	
    // Properties
    private ProgressDialog mProgressDialog;
    private JoinCarGroupActivity activity;
    private UserFunctions userFunction;
    private JSONObject json;
    private String carGroupId;
    private String password;
    private String error_msg;
	
	public JoinCarGroupTask(JoinCarGroupActivity activity, String carGroupId, String password)
	{
		this.activity = activity;
		this.carGroupId = carGroupId;
		this.password = password;
		error_msg = "";
	}
	
    @Override
    protected void onPostExecute(Void v) {
    	mProgressDialog.dismiss(); 
    	
    	if (activity != null) {	  
	        if (!error_msg.equals("")) {
	        	activity.showErrorMsg(error_msg);
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
        json = userFunction.joinCarGroup(activity.getApplicationContext(), carGroupId, password);
		// check for response
        try {
            if (json.getString(KEY_SUCCESS) != null) {
                String res = json.getString(KEY_SUCCESS);
                if(Integer.parseInt(res) == 1){
                    // successfully joined carGroup
                    
                	// Store user details in SQLite Database
                    DatabaseHandler db = new DatabaseHandler(activity.getApplicationContext());
                    db.joinCarGroup(carGroupId);     
                }else{
                	error_msg = json.getString(KEY_ERROR_MSG);             	
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}