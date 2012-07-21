package gogo.gadgeto.car;

import gogo.gadgeto.car.tasks.RegisterCarShareTask;
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
	private EditText currentMileageEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_car_group);
        
        createGroupButton = (Button) findViewById(R.id.createCarGroupSendButton);
        newPasswordEditText = (EditText) findViewById(R.id.CreateCarGroupNewPasswordEditText);
        repeatNewPasswordEditText = (EditText) findViewById(R.id.CreateCarGroupRepeatNewPasswordEditText);
        currentMileageEditText = (EditText) findViewById(R.id.createCarGroupEnterMileageEditText);
        
        createGroupButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				String newPassword = newPasswordEditText.getText().toString();
				String repeatNewPassword = repeatNewPasswordEditText.getText().toString();
				String currentMileage = currentMileageEditText.getText().toString();
				
				Boolean equal = CheckPasswords(newPassword, repeatNewPassword);
				if (equal) {
					// TODO: enter mileage! ask lars
					registerCarShare(newPassword, currentMileage);
				}
				else {
			    	newPasswordEditText.setText("");
			    	repeatNewPasswordEditText.setText("");
					Toast.makeText(getApplication(), "Passwords not equal.", Toast.LENGTH_LONG).show();					
				}			
			}
		});
        
    }

    protected void registerCarShare(String password, String currentMileage) {
    	new RegisterCarShareTask(this, password, currentMileage).execute();	
    	newPasswordEditText.setText("");
    	repeatNewPasswordEditText.setText("");
    }
    
    public void showError(String msg) {
    	Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    	    	
		Intent newIntent = new Intent(CreateCarGroupActivity.this, MainMenuActivity.class);
		startActivity(newIntent);
    }
    
    protected Boolean CheckPasswords(String newPassword,
			String repeatNewPassword) {
		Boolean result = newPassword.equals(repeatNewPassword);
		return result;
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_car_group, menu);
        return true;
    }

    
}
