package gogo.gadgeto.car;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	
	protected static final int TIMEOUT_MS = 0;
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
				
				String response = communicateWithServer("login", parameters);		
				debugOutput.setText("Debug: " + response );
				
				if (response.equals("Accept"))
				{
					Intent newIntent = new Intent(LoginActivity.this, MainMenuActivity.class);
					startActivity(newIntent);
				}
			}

			private String communicateWithServer(String command, Map<String, Editable> parameters) {
				
				String address = "http://le88.dyndns.org/android/php/CarSharing/" + command + ".php";
				
				HttpResponse response = null;
				String endResult = "";
								
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(address);
				
				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
				for (String key : parameters.keySet()) {
					pairs.add(new BasicNameValuePair(key, parameters.get(key).toString()));
				}
				
				try {
					post.setEntity(new UrlEncodedFormEntity(pairs));
					response = client.execute(post);
					
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				}
				
				BasicResponseHandler myHandler = new BasicResponseHandler();
				try {
					endResult = myHandler.handleResponse(response);
				} catch (HttpResponseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return endResult;
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }

    
}
