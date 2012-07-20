package gogo.gadgeto.car.helper;

import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
 
import android.content.Context;
 
public class UserFunctions {
 
    private JSONParser jsonParser;
 
    // Testing in localhost using wamp or xampp
    // use http://10.0.2.2/ to connect to your localhost ie http://localhost/
    private static String phpUrl = "http://le88.dyndns.org/android/php/android_api/";
 
    private static String loginUser_tag = "login";
    private static String registerUser_tag = "registerUser";
    
    private static String loginCarsShare_tag = "joinCarShare";
    private static String registerCarShare_tag = "registerCarShare";
    private static String getUsersInCarShare_tag = "getUsersInCarShare";
 
    // constructor
    public UserFunctions(){
        jsonParser = new JSONParser();
    }
 
    /**
     * function make Login Request
     * @param email
     * @param password
     * */
    public JSONObject loginUser(String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", loginUser_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(phpUrl, params);
        // return json
        // Log.e("JSON", json.toString());
        return json;
    }
 
    /**
     * function register new User
     * @param name
     * @param email
     * @param password
     * */
    public JSONObject registerUser(String name, String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", registerUser_tag));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
 
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(phpUrl, params);
        // return json
        return json;
    }
    
    /**
     * function register new User
     * @param email
     * @param carShareId
     * */
    public JSONObject loginCarshare(String email, String carShareId){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", loginCarsShare_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("id", carShareId));
 
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(phpUrl, params);
        // return json
        return json;
    }
    
    /**
     * function register new User
     * @param email
     * @param carShareId
     * */
    public JSONObject registerCarshare(String mileage, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", registerCarShare_tag));
        params.add(new BasicNameValuePair("mileage", mileage));
        params.add(new BasicNameValuePair("password", password));
 
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(phpUrl, params);
        // return json
        return json;
    }
    
    /**
     * function register new User
     * @param carShareId
     * */
    public JSONObject getUsersInCarShare(String carShareId){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", getUsersInCarShare_tag));
        params.add(new BasicNameValuePair("carShareId", carShareId));
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(phpUrl, params);
        // return json
        return json;
    }
    
    /**
     * Function get Login status
     * */
    public boolean isUserLoggedIn(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        int count = db.getRowCount();
        if(count > 0){
            // user logged in
            return true;
        }
        return false;
    }
    
    /**
     * Function get email from current logged-in user
     * */
    public String getEmailFromLoggedInUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        int count = db.getRowCount();
        if(count > 0){
            return db.getUserDetails().get("email");
        }
        return "";
    }
 
    /**
     * Function to logout user
     * Reset Database
     * */
    public boolean logoutUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        db.resetLogin();
        return true;
    }
 
}