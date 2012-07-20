package gogo.gadgeto.car;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class StatisticFragment extends Fragment{
	
	private Button cashDeptsButton;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View myView = inflater.inflate(R.layout.fragment_statistic, container, false);
		
		cashDeptsButton = (Button) myView.findViewById(R.id.statisticCashDeptsButton);
		
		cashDeptsButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent newIntent = new Intent(getActivity().getApplicationContext(), DeptActivity.class);
				startActivity(newIntent);				
			}
		});		
		
		return myView;
	}

}
