package gogo.gadgeto.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenuActivity extends Activity {
	
	private Button newDistanceButton;
	private Button fuelButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
        newDistanceButton = (Button) findViewById(R.id.newDistanceButton);        
        newDistanceButton.setOnClickListener(new OnClickListener() {
        	
			public void onClick(View v) {
				Intent newIntent = new Intent(MainMenuActivity.this, NewDistanceSelectDriverActivity.class);
				startActivity(newIntent);
			}
		});
        
        fuelButton = (Button) findViewById(R.id.fuelButton);
        fuelButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent newIntent = new Intent(MainMenuActivity.this, FuelSelectMemberActivity.class);
				startActivity(newIntent);				
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    
}
