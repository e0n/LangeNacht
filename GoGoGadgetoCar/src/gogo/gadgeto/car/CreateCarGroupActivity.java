package gogo.gadgeto.car;

import gogo.gadgeto.car.tasks.RegisterCarGroupTask;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateCarGroupActivity extends Activity {
	
	private Button createGroupButton;
	private EditText newPasswordEditText;
	private EditText repeatNewPasswordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_car_group);
        
        
        //connect GUI elements
        createGroupButton = (Button) findViewById(R.id.createCarGroupSendButton);
        newPasswordEditText = (EditText) findViewById(R.id.CreateCarGroupNewPasswordEditText);
        repeatNewPasswordEditText = (EditText) findViewById(R.id.CreateCarGroupRepeatNewPasswordEditText);
        
        // send create grp request to server
        createGroupButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				String newPassword = newPasswordEditText.getText().toString();
				String repeatNewPassword = repeatNewPasswordEditText.getText().toString();
				
				// compare passwords
				if (newPassword.equals(repeatNewPassword)) {
					registerCarGroup(newPassword);
				}
				else {
					// if nor equal, clean edit fields and output msg
			    	cleanEditText();
					Toast.makeText(getApplication(), "Passwords are not equal.", Toast.LENGTH_LONG).show();					
				}			
			}
		});
        
    }

    // start register grp task and clean edit fields
    protected void registerCarGroup(String password) {
    	new RegisterCarGroupTask(this, password).execute();	
    	cleanEditText();
    }
    
    public void showError(String msg) {
    	Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();    
    }
    
    public void carGroupWindow() {
		Intent newIntent = new Intent(CreateCarGroupActivity.this, MainMenuActivity.class);
		startActivity(newIntent);
    }
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_car_group, menu);
        return true;
    }

	private void cleanEditText() {
		newPasswordEditText.setText("");
		repeatNewPasswordEditText.setText("");
	}    
}
