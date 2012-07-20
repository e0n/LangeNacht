package gogo.gadgeto.car;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class JoinCarGroupActivity extends Fragment {
	
	private Button finishedButton;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View myView = inflater.inflate(R.layout.fragment_fuel, container, false);
		
		finishedButton = (Button) myView.findViewById(R.id.finishButton);
		
		finishedButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				FragmentManager manager = getFragmentManager();
				FragmentTransaction transaction = manager.beginTransaction();
				
				CarGroupFragment fragment = new CarGroupFragment();
				transaction.add(R.id.fragmentCarGroup, fragment);
				transaction.commit();
				
			}
		});
		
		return myView;
		
	}
    
}
