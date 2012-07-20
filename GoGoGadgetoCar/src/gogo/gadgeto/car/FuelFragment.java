package gogo.gadgeto.car;

import gogo.gadgeto.car.tasks.GetUsersTask;
import gogo.gadgeto.model.Database;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
	private EditText mileageEditText;
	private Spinner payerSpinner;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		
		database = Database.getInstance();
		View myView = inflater.inflate(R.layout.fragment_fuel, container, false);
		
		sendFuelButton = (Button) myView.findViewById(R.id.sendFuelButton);
		paymentEditText = (EditText) myView.findViewById(R.id.paymentEditText);
		mileageEditText = (EditText) myView.findViewById(R.id.fuelNewMileageEditText);
		payerSpinner = (Spinner) myView.findViewById(R.id.payerSpinner);
		
		new GetUsersTask(this, database.getCurrentCarShareId()).execute();
		
		sendFuelButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Editable paymentText = paymentEditText.getText();
				Editable mileageText = mileageEditText.getText();
				if (paymentText.length() != 0 && mileageText.length() != 0)
				{
					int payment = Integer.parseInt(paymentText.toString());
					int newMileage = Integer.parseInt(mileageText.toString());
					String result = database.sendFuelToDatabase((String) payerSpinner.getSelectedItem(), payment, newMileage, database.getUsername());
				
					Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getActivity(), "no valid payment or mileage", Toast.LENGTH_LONG).show();
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
