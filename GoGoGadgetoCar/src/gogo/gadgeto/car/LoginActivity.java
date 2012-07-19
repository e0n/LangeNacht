package gogo.gadgeto.car;

import gogo.gadgeto.car.helper.UserFunctions;
import gogo.gadgeto.car.tasks.LoginTask;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	private EditText useremail;
	private EditText userpassword;
	private Button loginButton;
	private Button registerButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        if (new UserFunctions().isUserLoggedIn(getApplicationContext())) {
        	// Launch MainMenu Screen
    		Intent newIntent = new Intent(LoginActivity.this, MainMenuActivity.class);
    		startActivity(newIntent);
        }
        
        useremail = (EditText) findViewById(R.id.userEmailEditText);
        userpassword = (EditText) findViewById(R.id.userPasswordEditText);
        loginButton = (Button) findViewById(R.id.logInButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        
        loginButton.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {    
				doLogIn();			} 	
        });	 
        
        registerButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent newIntent = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(newIntent);
			}
		});
    }					

    public void doLogIn() {
    	new LoginTask(this, useremail.getText().toString(), userpassword.getText().toString()).execute();	
    	useremail.setText("");
    	userpassword.setText("");
    }
    
    public void loginWindow() {
		Intent newIntent = new Intent(LoginActivity.this, MainMenuActivity.class);
		startActivity(newIntent);
    }
    
	public void showErrorMsg(String msg)
	{
       	Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    } 
}
    
