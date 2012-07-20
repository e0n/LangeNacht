package gogo.gadgeto.car;

import gogo.gadgeto.model.Database;

import java.util.Set;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewDistanceFragment extends Fragment {
	
	Database database;
	
	private Button sendDistanceButton;
	
	private EditText travelledDistance;
	
	private Button driverCount;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		database = Database.getInstance();
		View myView = inflater.inflate(R.layout.fragment_new_distance, container, false);
		
        sendDistanceButton = (Button) myView.findViewById(R.id.InsertDistanceButton);
        travelledDistance = (EditText) myView.findViewById(R.id.travelledDistanceEditText);
        driverCount = (Button) myView.findViewById(R.id.numberOfDriversTextView);        
        
        writeDriversCount();
        
        driverCount.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				Intent newIntent = new Intent(getActivity().getApplicationContext(), NewDistanceSelectDriverActivity.class);
				startActivity(newIntent);
			}
		});
        
        sendDistanceButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Editable text = travelledDistance.getText();
				if (text.length() != 0)
				{
					int distance = Integer.parseInt(text.toString());
					String result = database.sendDistanceToDatabase(database.getSelectedDriverNames(), distance, database.getUsername());
				
					Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getActivity(), "no valid distance", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		// Inflate the layout for this fragment
        return myView;
    }

	private void writeDriversCount() {
		
        Set<String> selectedNames = database.getSelectedDriverNames();
        if (selectedNames.size() == 0) {
        	database.toggleSelectedDriverName(database.getUsername());
        	driverCount.setText(database.getUsername());
        } else if (selectedNames.size() == 1) {
        	driverCount.setText(selectedNames.iterator().next());
        } else {
        	driverCount.setText("" + selectedNames.size());
        }
	}
	
	@Override
    public void onStart() {
		super.onStart();
		writeDriversCount();
	}
}
