package gogo.gadgeto.car;

import gogo.gadgeto.car.helper.UserFunctions;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LogoutFragment extends Fragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View myView = inflater.inflate(R.layout.fragment_logout, container, false);
		
		return myView;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		new UserFunctions().logoutUser(this.getActivity().getApplicationContext());
		
		Intent newIntent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
		startActivity(newIntent);		
	}
}
