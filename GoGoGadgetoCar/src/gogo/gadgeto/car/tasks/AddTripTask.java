package gogo.gadgeto.car.tasks;

import gogo.gadgeto.car.NewTripFragment;
import gogo.gadgeto.car.helper.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class AddTripTask extends AsyncTask<Void, Void, Void> {

    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR_MSG = "error_msg";
    
    // Properties
    private NewTripFragment fragment;
    private ProgressDialog mProgressDialog;
    private UserFunctions userFunction;
    private JSONObject json;
    private String distance;
    private String error_msg;
	
	public AddTripTask(NewTripFragment fragment, String distance)
	{
		this.fragment = fragment;
		this.distance = distance;
		this.error_msg = "";
	}
	
    @Override
	protected void onPostExecute(Void result) {
    	mProgressDialog.dismiss(); 
    	if (fragment != null) {
    		if (!error_msg.equals("")) {
    			fragment.showErrorMsg(error_msg);
    		}
    		else {
    			fragment.refreshFragment();
    		}
    	}	
	}
    
    @Override
    protected void onPreExecute() {
        mProgressDialog = ProgressDialog.show(fragment.getActivity(), "Loading...", "Fetching data from server...");
    }


    @Override
    protected Void doInBackground(Void... params) {        
        userFunction = new UserFunctions();
        json = userFunction.addTrip(fragment.getActivity().getApplicationContext(), distance);
		
		try {
            if (json.getString(KEY_SUCCESS) != null) {
                String res = json.getString(KEY_SUCCESS);
                if(Integer.parseInt(res) == 1){

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