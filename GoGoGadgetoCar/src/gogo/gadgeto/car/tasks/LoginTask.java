package gogo.gadgeto.car.tasks;

import gogo.gadgeto.car.LoginActivity;
import gogo.gadgeto.car.helper.DatabaseHandler;
import gogo.gadgeto.car.helper.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class LoginTask extends AsyncTask<Void, Void, Boolean> {

    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR_MSG = "error_msg";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_CARGROUPID = "cargroupid";
    private static String KEY_CREATED_AT = "created_at";
	
    // Properties
    private ProgressDialog mProgressDialog;
    private LoginActivity activity;
    private UserFunctions userFunction;
    private JSONObject json;
    private String email;
    private String password;
    private String error_msg;
	
	public LoginTask(LoginActivity activity, String email, String password)
	{
		this.activity = activity;
		this.email = email;
		this.password = password;
	}
	
    @Override
    protected void onPostExecute(Boolean result) {
        mProgressDialog.dismiss();   
        if (!result) {
        	activity.showErrorMsg(error_msg);
        }
    }

    @Override
    protected void onPreExecute() {
        mProgressDialog = ProgressDialog.show(activity, "Loading...", "Fetching data from server...");
    }

    @Override
    protected Boolean doInBackground(Void... params) {        
        userFunction = new UserFunctions();
        json = userFunction.loginUser(email, password);
        
		// check for login response
        try {
            if (json.getString(KEY_SUCCESS) != null) {
                String res = json.getString(KEY_SUCCESS);
                if(Integer.parseInt(res) == 1){
                    // user successfully logged in
                    // Store user details in SQLite Database
                    DatabaseHandler db = new DatabaseHandler(activity.getApplicationContext());
                    							
                    JSONObject json_user = json.getJSONObject("user");

                    // Clear all previous data in database
                    userFunction.logoutUser(activity.getApplicationContext());
                    db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL),json_user.getString(KEY_CARGROUPID), json_user.getString(KEY_CREATED_AT));                       
                    activity.loginWindow();
                    
                    return true;
                }else{
                	error_msg = json.getString(KEY_ERROR_MSG);             	
                	return false;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}