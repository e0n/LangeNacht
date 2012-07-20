package gogo.gadgeto.car;

import gogo.gadgeto.model.Database;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class LeaveCarGroupActivity extends Activity {
	
	private Spinner groupIdSpinner;
	private Button leaveGroupButton;
	
	private Database database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_car_group);
        
        groupIdSpinner = (Spinner) findViewById(R.id.LeaveGroupIdSpinner);
        leaveGroupButton = (Button) findViewById(R.id.LeaveGroupSendButton);
        
        database = Database.getInstance();
        
        String groupStrings[] = database.getGroups(database.getUsername());
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,groupStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupIdSpinner.setAdapter(adapter);
        
        
        leaveGroupButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				String leavingGroupId =  (String) groupIdSpinner.getSelectedItem();
				String result = database.sendLeaveGroupRequest(database.getUsername(), leavingGroupId);
				
				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
				
				Intent newIntent = new Intent(LeaveCarGroupActivity.this, MainMenuActivity.class);
	    		startActivity(newIntent);
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_leave_car_group, menu);
        return true;
    }

    
}
