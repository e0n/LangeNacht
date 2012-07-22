package gogo.gadgeto.car;

import gogo.gadgeto.car.helper.UserFunctions;
import gogo.gadgeto.car.tasks.LeaveCarGroupTask;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CarGroupFragment extends Fragment {
	
	private TextView currentGroupId;
	
	private Button joinGroupButton;
	private Button leaveGroupButton;
	private Button createGroupButton;
		
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myView = inflater.inflate(R.layout.fragment_car_group, container, false);
		
		currentGroupId = (TextView) myView.findViewById(R.id.currentCarGroupIdEntryTextView);
		joinGroupButton = (Button) myView.findViewById(R.id.joinCarGroupButton);
		leaveGroupButton = (Button) myView.findViewById(R.id.leaveCarGroupButton);
		createGroupButton = (Button) myView.findViewById(R.id.createCarGroupButton);
				

		// Display cargroupID
		String carGroupId = new UserFunctions().getCarGroupIdFromLoggedInUser(getActivity().getApplicationContext());
		
		if (!carGroupId.equals("null")) {
			currentGroupId.setText(carGroupId);
			joinGroupButton.setVisibility(View.INVISIBLE);
			leaveGroupButton.setVisibility(View.VISIBLE);
			createGroupButton.setVisibility(View.GONE);
		} else {
			currentGroupId.setText("(not in group)");
			joinGroupButton.setVisibility(View.VISIBLE);
			leaveGroupButton.setVisibility(View.GONE);
			createGroupButton.setVisibility(View.VISIBLE);
		}
		
		joinGroupButton.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				Intent newIntent = new Intent(getActivity().getApplicationContext(), JoinCarGroupActivity.class);
				startActivity(newIntent);
			}
		});
		
		leaveGroupButton.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				doLeave();
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
		
	private void doLeave() {
		new LeaveCarGroupTask(this).execute();
	}
	
    public void refreshFragment() {
		startActivity(getActivity().getIntent());
		getActivity().finish();
    }
    
	public void showErrorMsg(String msg) {
		if (!msg.equals("") && this != null) {
			Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_LONG).show();
		}
	}
}
