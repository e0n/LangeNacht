package gogo.gadgeto.car.tasks;

import gogo.gadgeto.car.CarGroupFragment;
import gogo.gadgeto.car.helper.DatabaseHandler;
import gogo.gadgeto.car.helper.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class LeaveCarGroupTask extends AsyncTask<Void, Void, Void> {

    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR_MSG = "error_msg";
	
    // Properties
    private ProgressDialog mProgressDialog;
    private CarGroupFragment fragment;
    private UserFunctions userFunction;
    private JSONObject json;
    private String error_msg;
	
	public LeaveCarGroupTask(CarGroupFragment fragment)
	{
		this.fragment = fragment;
		error_msg = "";
	}
	
    @Override
    protected void onPostExecute(Void v) {
    	if (fragment != null) {	  
	        if (!error_msg.equals("")) {
	        	fragment.showErrorMsg(error_msg);
	        }
	        else {
	        	fragment.refreshFragment();
	        }
        }
    	
    	mProgressDialog.dismiss(); 
    }

    @Override
    protected void onPreExecute() {
        mProgressDialog = ProgressDialog.show(fragment.getActivity(), "Loading...", "Fetching data from server...");
    }

    @Override
    protected Void doInBackground(Void... params) {        
        userFunction = new UserFunctions();
        json = userFunction.leaveCarGroup(fragment.getActivity().getApplicationContext());
		// check for response
        try {
            if (json.getString(KEY_SUCCESS) != null) {
                String res = json.getString(KEY_SUCCESS);
                if(Integer.parseInt(res) == 1){
                    // successfully joined carGroup
                    
                	// Store user details in SQLite Database
                    DatabaseHandler db = new DatabaseHandler(fragment.getActivity().getApplicationContext());
                    db.leaveCarGroup();     
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