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
        
        createGroupButton = (Button) findViewById(R.id.createCarGroupSendButton);
        newPasswordEditText = (EditText) findViewById(R.id.CreateCarGroupNewPasswordEditText);
        repeatNewPasswordEditText = (EditText) findViewById(R.id.CreateCarGroupRepeatNewPasswordEditText);
        
        createGroupButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				String newPassword = newPasswordEditText.getText().toString();
				String repeatNewPassword = repeatNewPasswordEditText.getText().toString();
				
				if (newPassword.equals(repeatNewPassword)) {
					registerCarGroup(newPassword);
				}
				else {
			    	cleanEditText();
					Toast.makeText(getApplication(), "Passwords are not equal.", Toast.LENGTH_LONG).show();					
				}			
			}
		});
        
    }

    protected void registerCarGroup(String password) {
    	new RegisterCarGroupTask(this, password).execute();	
    	cleanEditText();
    }
    
    public void showError(String msg) {
    	Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();    
    }
    
    public void carGroupWindow() {
		Intent newIntent = new Intent(CreateCarGroupActivity.this, CarGroupFragment.class);
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
