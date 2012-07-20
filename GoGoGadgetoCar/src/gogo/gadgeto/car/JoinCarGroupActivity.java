package gogo.gadgeto.car;

import gogo.gadgeto.model.Database;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinCarGroupActivity extends Activity {
	
	private Button sendButton;
	private EditText carGroupIdEditText;
	private EditText carGroupPasswordEditText;
	private Database database;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_car_group);
		
		sendButton = (Button) findViewById(R.id.joinCarGroupSendButton);
		carGroupIdEditText = (EditText) findViewById(R.id.joinCarGroupIdEditText);
		carGroupPasswordEditText = (EditText) findViewById(R.id.joinCarGroupPasswordEditText);
		
		database = Database.getInstance();
		
		sendButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				String groupId = carGroupIdEditText.getText().toString();
				String password = carGroupPasswordEditText.getText().toString();
				String result = database.sendJoinCarGroupRequest(database.getUsername(), groupId, password);
				
				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
				
				Intent newIntent = new Intent(JoinCarGroupActivity.this, MainMenuActivity.class);
	    		startActivity(newIntent);
			}
		});
		

		
	}
    
}
