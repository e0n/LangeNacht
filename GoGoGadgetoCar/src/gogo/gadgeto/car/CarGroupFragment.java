package gogo.gadgeto.car;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CarGroupFragment extends Fragment {
	
	private Button joinGroupButton;
	private Button leaveGroupButton;
	private Button createGroupButton;
	
//	public static CarGroupFragment INSTANCE;
//
//	public CarGroupFragment() {
//		super();
//		INSTANCE = new CarGroupFragment();
//	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myView = inflater.inflate(R.layout.fragment_car_group, container, false);
		
		joinGroupButton = (Button) myView.findViewById(R.id.joinCarGroupButton);
		leaveGroupButton = (Button) myView.findViewById(R.id.leaveCarGroupButton);
		createGroupButton = (Button) myView.findViewById(R.id.createCarGroupButton);
		
		joinGroupButton.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				Intent newIntent = new Intent(getActivity().getApplicationContext(), FuelSelectMemberActivity.class);
				startActivity(newIntent);
			}
		});
		
		leaveGroupButton.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				Intent newIntent = new Intent(getActivity().getApplicationContext(), FuelSelectMemberActivity.class);
				startActivity(newIntent);
			}
		});
		
		createGroupButton.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				Intent newIntent = new Intent(getActivity().getApplicationContext(), FuelSelectMemberActivity.class);
				startActivity(newIntent);
			}
		});
		
		return myView;
		
	}
}
