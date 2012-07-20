package gogo.gadgeto.car.tasks;

import gogo.gadgeto.car.FuelFragment;
import gogo.gadgeto.car.helper.UserFunctions;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class GetUsersTask extends AsyncTask<Void, Void, Void> {

    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR_MSG = "error_msg";
    private static String KEY_NAME = "name";
    
    // Properties
    private FuelFragment fragment;
    private UserFunctions userFunction;
    private JSONObject json;
    private String carShareId;
    private String error_msg;
    List<String> daten;
	
	public GetUsersTask(FuelFragment fragment, String carShareId)
	{
		this.fragment = fragment;
	    this.carShareId = carShareId;
		this.daten = new ArrayList<String>();
		this.error_msg = "";
	}
	
    @Override
	protected void onPostExecute(Void result) {
	    	fragment.updateSpinner(daten, error_msg);
	}

    @Override
    protected Void doInBackground(Void... params) {        
        userFunction = new UserFunctions();
        json = userFunction.getUsersInCarShare(carShareId);
		
		try {
			if (json.getString(KEY_SUCCESS) != null) {
			    String res = json.getString(KEY_SUCCESS);
			    if(Integer.parseInt(res) == 1){		    	
			    	JSONArray jsonArray = json.getJSONArray("users");
			    	
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObjectTmp = jsonArray.getJSONObject(i);
						daten.add(jsonObjectTmp.getString(KEY_NAME));			
					}
			    }
			}
			else {
				error_msg = json.getString(KEY_ERROR_MSG);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }
}