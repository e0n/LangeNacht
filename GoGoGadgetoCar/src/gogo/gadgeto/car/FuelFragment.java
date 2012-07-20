package gogo.gadgeto.car;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gogo.gadgeto.car.helper.DatabaseHandler;
import gogo.gadgeto.car.helper.UserFunctions;
import gogo.gadgeto.car.tasks.GetUsersTask;
import gogo.gadgeto.model.Database;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FuelFragment extends Fragment {
	
    // JSON Response node names
    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";
    private static String KEY_ERROR_MSG = "error_msg";
    private static String KEY_UID = "uid";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";
	
	Database database;
	
	private Button sendFuelButton;
	private EditText paymentEditText;
	private Spinner payerSpinner;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		
		database = Database.getInstance();
		View myView = inflater.inflate(R.layout.fragment_fuel, container, false);
		
		sendFuelButton = (Button) myView.findViewById(R.id.sendFuelButton);
		paymentEditText = (EditText) myView.findViewById(R.id.paymentEditText);
		payerSpinner = (Spinner) myView.findViewById(R.id.payerSpinner);
		
		new GetUsersTask(this, database.getCurrentCarShareId()).execute();
		
		sendFuelButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Editable text = paymentEditText.getText();
				if (text.length() != 0)
				{
					int payment = Integer.parseInt(text.toString());
					String result = database.sendFuelToDatabase(database.getSelectedPayerNames(), payment, database.getUsername());
				
					Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getActivity(), "no valid payment", Toast.LENGTH_LONG).show();
				}
			}
		});
		
        return myView;
    }
	
	public void updateSpinner(List<String> daten, String error_msg) {
		
		Activity activity;
		
		if ((activity = getActivity()) != null) {		
			if(error_msg.isEmpty()) {
				ArrayAdapter<String> datenAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, daten);
				datenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				payerSpinner.setAdapter(datenAdapter);	
			}
			else {
				Toast.makeText(activity, error_msg, Toast.LENGTH_SHORT).show();
			}
		}
	
	}    
}
