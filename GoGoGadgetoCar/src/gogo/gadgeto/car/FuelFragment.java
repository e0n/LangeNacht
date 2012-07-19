package gogo.gadgeto.car;

import java.util.Set;

import gogo.gadgeto.model.Database;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FuelFragment extends Fragment {
	
	Database database;
	
	private Button changePayerButton;
	private Button sendFuelButton;
	
	private EditText paymentEditText;
	
	private TextView payerCountTextView;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		
		database = Database.getInstance();
		View myView = inflater.inflate(R.layout.fragment_fuel, container, false);
		
		changePayerButton = (Button) myView.findViewById(R.id.changePayerButton);
		sendFuelButton = (Button) myView.findViewById(R.id.sendFuelButton);
        
		paymentEditText = (EditText) myView.findViewById(R.id.paymentEditText);
        
		payerCountTextView = (TextView) myView.findViewById(R.id.payerDisplayTextView);
		
		writePayerCount();
        
		changePayerButton.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				Intent newIntent = new Intent(getActivity().getApplicationContext(), FuelSelectMemberActivity.class);
				startActivity(newIntent);
			}
		});
		
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

	private void writePayerCount() {
		Set<String> selectedNames = database.getSelectedPayerNames();
        if (selectedNames.size() == 0) {
        	database.toggleSelectedDriverName(database.getUsername());
        	payerCountTextView.setText(database.getUsername());
        } else if (selectedNames.size() == 1) {
        	payerCountTextView.setText(selectedNames.iterator().next());
        } else {
        	payerCountTextView.setText(" " + selectedNames.size());
        }		
	}
	
	@Override
    public void onStart() {
		super.onStart();
		writePayerCount();
	}

    
}
