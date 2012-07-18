package gogo.gadgeto.car;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class NewDistanceFragment extends Fragment {
	
	private Button changeDriversButton;
	private Button sendDistanceButton;
	
	private EditText travelledDistance;
	
	private TextView driverCount;
	
	private Spinner driverSprinner;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myView = inflater.inflate(R.layout.fragment_new_distance, container, false);
		
		changeDriversButton = (Button) myView.findViewById(R.id.changeDriversButton);
        sendDistanceButton = (Button) myView.findViewById(R.id.InsertDistanceButton);
        
        travelledDistance = (EditText) myView.findViewById(R.id.travelledDistanceEditText);
        
        driverCount = (TextView) myView.findViewById(R.id.numberOfDriversTextView);
        
        driverSprinner = (Spinner) myView.findViewById(R.id.driversSpinner);
        
        driverCount.setText(" " + 1);
        
        changeDriversButton.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				Intent newIntent = new Intent(getActivity().getApplicationContext(), NewDistanceSelectDriverActivity.class);
				startActivity(newIntent);
			}
		});
        
        sendDistanceButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				//TODO: write insert into DB
			}
		});
		
		// Inflate the layout for this fragment
        return myView;
    }    
}
