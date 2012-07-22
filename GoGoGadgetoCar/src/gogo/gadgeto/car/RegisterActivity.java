package gogo.gadgeto.car;

import gogo.gadgeto.car.helper.EmailCheck;
import gogo.gadgeto.car.tasks.RegisterUserTask;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity  extends Activity  {
	
	private EditText username;
	private EditText userpassword;
	private EditText useremail;
	private Button registerButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
        
        username = (EditText) findViewById(R.id.register_userNameEditText);
        useremail = (EditText) findViewById(R.id.register_userEmailEditText);
        userpassword = (EditText) findViewById(R.id.register_userPasswordEditText);
        registerButton = (Button) findViewById(R.id.register_registerButton);
        
        registerButton.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {   
				boolean result = new EmailCheck().isEmailAdress(useremail.getText().toString());				
				if (result) {
					doRegister();
				}
				else {
					Toast.makeText(getApplication(), "Invalid email.", Toast.LENGTH_SHORT).show();
				}
			}	
        });	 
	}
	
	private void doRegister() {
	    	new RegisterUserTask(this, username.getText().toString(), useremail.getText().toString(), userpassword.getText().toString()).execute();	
	    	username.setText("");
	    	userpassword.setText("");
	    	useremail.setText("");
    }

    public void loginWindow() {
		Intent newIntent = new Intent(RegisterActivity.this, LoginActivity.class);
		startActivity(newIntent);
    }
	
	public void showErrorMsg(String msg)
	{
	   	Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
	}	
}
