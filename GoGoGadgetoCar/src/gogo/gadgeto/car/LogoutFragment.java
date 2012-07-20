package gogo.gadgeto.car;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class LogoutFragment extends Fragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View myView = inflater.inflate(R.layout.activity_login, container, false);
		
		Toast.makeText(getActivity(), "Fickt euch penise", Toast.LENGTH_LONG).show();
		
//		Intent newIntent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
//		startActivity(newIntent);
		
		return myView;
	}
}
