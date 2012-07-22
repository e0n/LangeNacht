package gogo.gadgeto.car.tasks;

import gogo.gadgeto.car.CashEntry;
import gogo.gadgeto.car.DeptActivity;
import gogo.gadgeto.car.helper.UserFunctions;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class GetUserstatisticTask extends AsyncTask<Void, Void, Void> {

    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR_MSG = "error_msg";
    private static String KEY_AMOUNT = "amount";
    private static String KEY_NAME = "name";
    private static String KEY_CASHENTRIES = "statistics";
    private static String KEY_MILEAGE = "mileage_driven";

    
    // Properties
    private ProgressDialog mProgressDialog;
    private DeptActivity activity;
    private UserFunctions userFunction;
    private JSONObject json;
    private String error_msg;
    private Set<CashEntry> cashEntries;
    private String refuelid;
	
	public GetUserstatisticTask(DeptActivity activity, String refuelid)
	{
		this.activity = activity;
		this.refuelid = refuelid;
		this.cashEntries = new HashSet<CashEntry>();
		this.error_msg = "";
	}
	
    @Override
    protected void onPostExecute(Void v) {
        mProgressDialog.dismiss();    
        if(activity != null) {
	        if (error_msg.equals("")) {
	        	activity.refreshView(cashEntries);
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
        json = userFunction.getUserStatistics(refuelid);
        
		// check for login response
        try {
            if (json.getString(KEY_SUCCESS) != null) {
                String res = json.getString(KEY_SUCCESS);
                if(Integer.parseInt(res) == 1){
                    // successful response

                	JSONArray json_cashEntries = json.getJSONArray(KEY_CASHENTRIES);
                                    	
                    for (int i = 0; i < json_cashEntries.length(); i++) {
						JSONObject json_cashEntry = json_cashEntries.getJSONObject(i);
						cashEntries.add(new CashEntry(json_cashEntry.getString(KEY_NAME), json_cashEntry.getString(KEY_AMOUNT), json_cashEntry.getString(KEY_MILEAGE)));
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