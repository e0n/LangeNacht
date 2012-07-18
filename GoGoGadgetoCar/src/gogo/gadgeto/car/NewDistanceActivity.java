package gogo.gadgeto.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class NewDistanceActivity extends Activity {
	
	private Button changeDriversButton;
	private Button sendDistanceButton;
	private Button backToMainMenuButton;
	
	private EditText travelledDistance;
	
	private TextView driverCount;
	
	private Spinner driverSprinner;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_distance);
        
        changeDriversButton = (Button) findViewById(R.id.changeDriversButton);
        sendDistanceButton = (Button) findViewById(R.id.InsertDistanceButton);
        backToMainMenuButton = (Button) findViewById(R.id.backToMainMenuButton);
        
        travelledDistance = (EditText) findViewById(R.id.travelledDistanceEditText);
        
        driverCount = (TextView) findViewById(R.id.numberOfDriversTextView);
        
        driverSprinner = (Spinner) findViewById(R.id.driversSpinner);
        
        driverCount.setText(" " + 1);
        
        backToMainMenuButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent newIntent = new Intent(NewDistanceActivity.this, MainMenuActivity.class);
				startActivity(newIntent);				
			}
		});
        
        changeDriversButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent newIntent = new Intent(NewDistanceActivity.this, NewDistanceSelectDriverActivity.class);
				startActivity(newIntent);
			}
		});
        
        sendDistanceButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				//TODO: write insert into DB
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_new_distance, menu);
        return true;
    }

    
}
