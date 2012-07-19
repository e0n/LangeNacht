package gogo.gadgeto.car;

import gogo.gadgeto.car.helper.HelperClass;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
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
								
				Map<String,Editable> parameters = new HashMap<String, Editable>();
				parameters.put("username", username.getText());
				parameters.put("userpass", userpassword.getText());
								
				new RetreiveResponseTask().execute("userLogin", parameters);		
			}					
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    } 
    
    private void setResponse(String response) {
    	debugOutput.setText("Debug: " + response );
		
		if (response.equals("Accept"))
		{
			Intent newIntent = new Intent(LoginActivity.this, MainMenuActivity.class);
			startActivity(newIntent);
		}
    }  
    
    public class RetreiveResponseTask extends AsyncTask<Object, Void, String> {
    	
        protected String doInBackground(Object... urls) {
        	try {String command = (String)(urls[0]);
            	Map<String, Editable> parameters = (Map<String, Editable>) (urls[1]); 
                return HelperClass.communicateWithServer(command, parameters);
            } catch (Exception e) {
                return null;
            }
        }

        protected void onPostExecute(String response) {
        	setResponse(response);
        }
     }
}
