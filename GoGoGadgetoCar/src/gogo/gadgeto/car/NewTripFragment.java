package gogo.gadgeto.car;

import gogo.gadgeto.car.helper.UserFunctions;
import gogo.gadgeto.car.tasks.AddRefuelTask;
import gogo.gadgeto.car.tasks.AddTripTask;
import gogo.gadgeto.model.Database;

import java.util.Set;

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

public class NewTripFragment extends Fragment {
	
	Database database;
	
	private Button sendDistanceButton;
	
	private EditText travelledDistance;
	
	private TextView driverName;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		database = Database.getInstance();
		View myView = inflater.inflate(R.layout.fragment_new_trip, container, false);
		
        sendDistanceButton = (Button) myView.findViewById(R.id.InsertDistanceButton);
        travelledDistance = (EditText) myView.findViewById(R.id.travelledDistanceEditText);
        driverName = (TextView) myView.findViewById(R.id.distanceDriverNameTextView);        
        
        driverName.setText(new UserFunctions().getNameFromLoggedInUser(getActivity().getApplicationContext()));
        
        sendDistanceButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Editable text = travelledDistance.getText();
				if (text.length() != 0)
				{
					try {
						Integer distance = Integer.parseInt(text.toString());
						doTrip(distance.toString());
						cleanEditText();		
					} catch (Exception e) {
						cleanEditText();
						Toast.makeText(getActivity(), "No valid distance!", Toast.LENGTH_LONG).show();
					}
				} else {
					cleanEditText();
					Toast.makeText(getActivity(), "No valid distance!", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		// Inflate the layout for this fragment
        return myView;
    }
	
	private void doTrip(String distance) {
		new AddTripTask(this, distance).execute();
	}
	
	private void cleanEditText() {
		travelledDistance.setText("");
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
