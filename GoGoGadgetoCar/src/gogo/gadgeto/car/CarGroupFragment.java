package gogo.gadgeto.car;

import gogo.gadgeto.model.Database;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CarGroupFragment extends Fragment {
	
	private TextView currentGroupId;
	
	private Button joinGroupButton;
	private Button leaveGroupButton;
	private Button createGroupButton;
	
	private Database database;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myView = inflater.inflate(R.layout.fragment_car_group, container, false);
		
		currentGroupId = (TextView) myView.findViewById(R.id.currentCarGroupIdEntryTextView);
		joinGroupButton = (Button) myView.findViewById(R.id.joinCarGroupButton);
		leaveGroupButton = (Button) myView.findViewById(R.id.leaveCarGroupButton);
		createGroupButton = (Button) myView.findViewById(R.id.createCarGroupButton);
		
		database = Database.getInstance();
		
		currentGroupId.setText(database.getCurrentCarShareId());
		
		joinGroupButton.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				Intent newIntent = new Intent(getActivity().getApplicationContext(), JoinCarGroupActivity.class);
				startActivity(newIntent);
			}
		});
		
		leaveGroupButton.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				Intent newIntent = new Intent(getActivity().getApplicationContext(), LeaveCarGroupActivity.class);
				startActivity(newIntent);
			}
		});
		
		createGroupButton.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				Intent newIntent = new Intent(getActivity().getApplicationContext(), CreateCarGroupActivity.class);
				startActivity(newIntent);
			}
		});
		
		return myView;
		
	}
}
