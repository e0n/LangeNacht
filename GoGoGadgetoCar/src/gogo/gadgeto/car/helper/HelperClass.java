package gogo.gadgeto.car.helper;

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

import android.text.Editable;

public class HelperClass {
	
	public static String communicateWithServer(String command, Map<String, Editable> parameters) {
		
		String address = "http://le88.dyndns.org/android/php/CarSharing/" + command + ".php";
		
		HttpResponse httpResponse = null;
		String result = "";
						
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(address);
		
		
		List<NameValuePair> listPairs = new ArrayList<NameValuePair>();
		for (String key : parameters.keySet()) {
			listPairs.add(new BasicNameValuePair(key, parameters.get(key).toString()));
		}
		
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(listPairs));
			httpResponse = httpClient.execute(httpPost);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BasicResponseHandler basicResponseHandler = new BasicResponseHandler();
		
		try {
			result = basicResponseHandler.handleResponse(httpResponse);
		} catch (HttpResponseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
    
	public static String communicateWithServer(String command) {
		
		return communicateWithServer(command, new HashMap<String,Editable>());
	}

	
}
