package gogo.gadgeto.car;

import gogo.gadgeto.car.tasks.JoinCarGroupTask;
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
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join_car_group);
		
		sendButton = (Button) findViewById(R.id.joinCarGroupSendButton);
		carGroupIdEditText = (EditText) findViewById(R.id.joinCarGroupIdEditText);
		carGroupPasswordEditText = (EditText) findViewById(R.id.joinCarGroupPasswordEditText);
	
		sendButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				String groupId = carGroupIdEditText.getText().toString();
				String password = carGroupPasswordEditText.getText().toString();
				
				doJoin(groupId, password);
			}
		});		
	}
	
	private void doJoin(String carGroupId, String password) {
		new JoinCarGroupTask(this, carGroupId, password).execute();
	}
	
    public void carGroupWindow() {    
		Intent newIntent = new Intent(JoinCarGroupActivity.this, MainMenuActivity.class);
		startActivity(newIntent);
    }
	
	public void showErrorMsg(String msg) {
		if (!msg.equals("") && this != null) {
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
		}
	}
    
}
