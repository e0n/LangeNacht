package gogo.gadgeto.car.tasks;

import gogo.gadgeto.car.FuelEntry;
import gogo.gadgeto.car.StatisticFragment;
import gogo.gadgeto.car.helper.UserFunctions;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class GetRefuelingsTask extends AsyncTask<Void, Void, Void> {

    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR_MSG = "error_msg";
    private static String KEY_AMOUNT = "amount";
    private static String KEY_MILEAGE = "mileage_driven";
    private static String KEY_CREATED_AT = "created_at";
    private static String KEY_FUELID = "refuelid";
    private static String KEY_FUELENTRIES = "fuelEntries";

    
    // Properties
    private ProgressDialog mProgressDialog;
    private StatisticFragment fragment;
    private UserFunctions userFunction;
    private JSONObject json;
    private String error_msg;
    private Set<FuelEntry> fuelEntries;
	
	public GetRefuelingsTask(StatisticFragment fragment)
	{
		this.fragment = fragment;
		this.fuelEntries = new HashSet<FuelEntry>();
		this.error_msg = "";
	}
	
    @Override
    protected void onPostExecute(Void v) {
        mProgressDialog.dismiss();    
        if(fragment != null) {
	        if (error_msg.equals("")) {
	        	fragment.refreshData(fuelEntries);
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
        json = userFunction.getRefuelings(fragment.getActivity().getApplicationContext());
        
		// check for login response
        try {
            if (json.getString(KEY_SUCCESS) != null) {
                String res = json.getString(KEY_SUCCESS);
                if(Integer.parseInt(res) == 1){
                    // successful response

                	JSONArray json_fuelEntries = json.getJSONArray(KEY_FUELENTRIES);
                                    	
                    for (int i = 0; i < json_fuelEntries.length(); i++) {
						JSONObject json_fuelEntry = json_fuelEntries.getJSONObject(i);
						fuelEntries.add(new FuelEntry(	json_fuelEntry.getString(KEY_CREATED_AT),
														json_fuelEntry.getString(KEY_MILEAGE) + " km",
														json_fuelEntry.getString(KEY_AMOUNT) + " €",
														json_fuelEntry.getString(KEY_FUELID)));
					}
                } else{
                	error_msg = json.getString(KEY_ERROR_MSG);             	
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}