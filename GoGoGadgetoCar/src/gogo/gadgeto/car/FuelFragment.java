package gogo.gadgeto.car;

import gogo.gadgeto.car.helper.UserFunctions;
import gogo.gadgeto.car.tasks.AddRefuelTask;
import gogo.gadgeto.model.Database;
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
	
	Database database;
	
	private Button sendFuelButton;
	private EditText paymentEditText;
	private EditText mileageEditText;
	private TextView payerName;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		
		database = Database.getInstance();
		View myView = inflater.inflate(R.layout.fragment_fuel, container, false);
		
		sendFuelButton = (Button) myView.findViewById(R.id.sendFuelButton);
		paymentEditText = (EditText) myView.findViewById(R.id.paymentEditText);
		mileageEditText = (EditText) myView.findViewById(R.id.fuelNewMileageEditText);
		payerName = (TextView) myView.findViewById(R.id.payerNameTextView);
		
		payerName.setText(new UserFunctions().getNameFromLoggedInUser(getActivity().getApplicationContext()));
		
		
		sendFuelButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				String paymentText = paymentEditText.getText().toString();
				String mileageText = mileageEditText.getText().toString();
				
				if (paymentText.length() != 0 &&mileageText.length() != 0)
				{
					try {
						Integer payment = Integer.parseInt(paymentText.toString());
						Integer newMileage = Integer.parseInt(mileageText.toString());
						
						doRefuel(payment.toString(), newMileage.toString());
						
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
	
	private void doRefuel(String amount, String mileage) {
		new AddRefuelTask(this, amount, mileage).execute();
	}
	
	private void cleanEditText() {
		paymentEditText.setText("");
		mileageEditText.setText("");
	}   
	
    public void refreshFragment() {
    	cleanEditText();    	
		startActivity(getActivity().getIntent());
		getActivity().finish();
    }
    
	public void showErrorMsg(String msg) {
		if (!msg.equals("") && this != null) {
			Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_LONG).show();
		}
	}
}
