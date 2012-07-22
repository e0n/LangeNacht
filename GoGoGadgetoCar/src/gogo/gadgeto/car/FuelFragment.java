package gogo.gadgeto.car;

import gogo.gadgeto.model.Database;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
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
		
		payerName.setText(database.getUsername());
		
		
		sendFuelButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Editable paymentText = paymentEditText.getText();
				Editable mileageText = mileageEditText.getText();
				if (paymentText.length() != 0 && mileageText.length() != 0)
				{
					int payment = Integer.parseInt(paymentText.toString());
					int newMileage = Integer.parseInt(mileageText.toString());
					String result = database.sendFuelToDatabase(database.getUsername(), payment, newMileage, database.getUsername());
				
					Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getActivity(), "No valid payment or mileage", Toast.LENGTH_LONG).show();
				}
			}
		});
		
        return myView;
    } 
}
