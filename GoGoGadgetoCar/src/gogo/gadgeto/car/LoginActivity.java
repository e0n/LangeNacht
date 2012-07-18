package gogo.gadgeto.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	
	private EditText username;
	private EditText userpassword;
	private Button loginButton;
	private TextView debugOutput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        username = (EditText) findViewById(R.id.userNameEditText);
        userpassword = (EditText) findViewById(R.id.userPasswordEditText);
        loginButton = (Button) findViewById(R.id.logInButton);
        debugOutput = (TextView) findViewById(R.id.DebugOutputTextView);
        
        loginButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				debugOutput.setText("Debug: " + username.getText() + " " + userpassword.getText());
				
				Intent newIntent = new Intent(LoginActivity.this, MainMenuActivity.class);
				startActivity(newIntent);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }

    
}
