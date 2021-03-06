package gogo.gadgeto.car;

import gogo.gadgeto.car.helper.UserFunctions;
import gogo.gadgeto.car.tasks.AddRefuelTask;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FuelFragment extends Fragment {
		
	private Button sendFuelButton;
	private EditText paymentEditText;
	private EditText mileageEditText;
	private TextView payerName;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		
		View myView = inflater.inflate(R.layout.fragment_fuel, container, false);
		
		// connect GUI elements
		sendFuelButton = (Button) myView.findViewById(R.id.sendFuelButton);
		paymentEditText = (EditText) myView.findViewById(R.id.paymentEditText);
		mileageEditText = (EditText) myView.findViewById(R.id.fuelNewMileageEditText);
		payerName = (TextView) myView.findViewById(R.id.payerNameTextView);
		
		// set Username as paying person
		payerName.setText(new UserFunctions().getNameFromLoggedInUser(getActivity().getApplicationContext()));
		
		// start fuel request
		sendFuelButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// read costs and mileage
				String paymentText = paymentEditText.getText().toString();
				String mileageText = mileageEditText.getText().toString();
				int paymentInt = 0;
				
				// if correct inputs
				if (paymentText.length() != 0 && mileageText.length() != 0)
				{
					try {
						// if double value, get cent and remove seperation
						if (paymentText.contains(".")) {
							Double doubleValue = Double.parseDouble(paymentText) * 100;
							paymentInt = Integer.parseInt(doubleValue.toString().split("\\.")[0]);	
						} 
						// if int, get cent
						else {
							paymentInt = Integer.parseInt(paymentText) * 100;
						}					
						Integer newMileage = Integer.parseInt(mileageText);
						// do a refuel
						doRefuel(String.valueOf(paymentInt), newMileage.toString());
					
					} catch (Exception e) {
						cleanEditText();
						Toast.makeText(getActivity(), "No valid payment or mileage!", Toast.LENGTH_LONG).show();
					}

				} else {
					cleanEditText();
					Toast.makeText(getActivity(), "No valid payment or mileage", Toast.LENGTH_LONG).show();
				}
			}
		});
		
        return myView;
    }
	
	
	// send refuel request
	private void doRefuel(String payment, String mileage) {
		new AddRefuelTask(this, payment, mileage).execute();
	}
	
	// clean payment and mileage edittext
	private void cleanEditText() {
		paymentEditText.setText("");
		mileageEditText.setText("");
	}   
	
	// refresh fragment
    public void refreshFragment() {
    	cleanEditText();    	
    }
    
	public void showErrorMsg(String msg) {
		if (!msg.equals("") && this != null) {
			Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_LONG).show();
		}
	}
}
