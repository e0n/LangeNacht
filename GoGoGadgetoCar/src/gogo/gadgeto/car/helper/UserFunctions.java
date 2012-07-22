package gogo.gadgeto.car.helper;

import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
 
import android.content.Context;
import android.util.Log;
 
public class UserFunctions {
 
    private JSONParser jsonParser;
 
    // Testing in localhost using wamp or xampp
    // use http://10.0.2.2/ to connect to your localhost ie http://localhost/
    //private static String phpUrl = "http://10.0.2.2/android/php/cargroup/";
    private static String phpUrl = "http://le88@dyndns.org/android/php/cargroup/";
 
    private static String user_login_tag = "loginUser";
    private static String user_register_tag = "registerUser";
    
    private static String carsgroup_register_tag = "registerCarGroup";
    private static String cargroup_join_tag = "joinCarGroup";
    private static String cargroup_leave_tag = "leaveCarGroup";
 
    private static String cargroup_addTrip_tag = "addTrip";
    private static String cargroup_addRefuel_tag = "addRefuel";
    
    private static String cargroup_refuelings_tag = "getRefuelings";
    private static String cargroup_userstatistics_tag = "getUserStatistics";
    
    // constructor
    public UserFunctions(){
        jsonParser = new JSONParser();
    }
 
    /**
     * @function make Login Request
     * @param email
     * @param password
     * */
    public JSONObject loginUser(String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", user_login_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(phpUrl, params);
        // return json
        // Log.e("JSON", json.toString());
        return json;
    }
 
    /**
     * @function register new User
     * @param name
     * @param email
     * @param password
     * */
    public JSONObject registerUser(String name, String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", user_register_tag));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
 
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(phpUrl, params);
        // return json
        return json;
    }
    
    
    /**
     * @function register new cargroup
     * @param password
     * */
    public JSONObject registerCarGroup(String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", carsgroup_register_tag));
        params.add(new BasicNameValuePair("password", password));
 
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(phpUrl, params);
        // return json
        return json;
    }
    
    /**
     * @function join existing cargroup
     * @param context
     * @param carGroupId
     * @param cargrouppassword
     * */
    public JSONObject joinCarGroup(Context context, String carGroupId, String cargrouppassword){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", cargroup_join_tag));
        params.add(new BasicNameValuePair("email", getEmailFromLoggedInUser(context)));
        params.add(new BasicNameValuePair("cargroupid", carGroupId));
        params.add(new BasicNameValuePair("cargrouppassword", cargrouppassword));
        
        Log.i("test", params.toString());
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(phpUrl, params);
        // return json
        return json;
    }
    
    /**
     * @function leave existing cargroup
     * @param context
     * */
    public JSONObject leaveCarGroup(Context context){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", cargroup_leave_tag));
        params.add(new BasicNameValuePair("email", getEmailFromLoggedInUser(context)));
        params.add(new BasicNameValuePair("cargroupid", getCarGroupIdFromLoggedInUser(context)));
        
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(phpUrl, params);
        // return json
        return json;
    }
    
    /**
     * @function add trip
     * @param context
     * @param mileage
     * */
    public JSONObject addTrip(Context context, String mileage){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", cargroup_addTrip_tag));
        params.add(new BasicNameValuePair("email", getEmailFromLoggedInUser(context)));
        params.add(new BasicNameValuePair("cargroupid", getCarGroupIdFromLoggedInUser(context)));
        params.add(new BasicNameValuePair("mileage", mileage));
        
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(phpUrl, params);
        // return json
        return json;
    }
    
    /**
     * @function add refuel
     * @param context
     * @param mileage
     * */
    public JSONObject addRefuel(Context context, String amount, String mileage){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", cargroup_addRefuel_tag));
        params.add(new BasicNameValuePair("email", getEmailFromLoggedInUser(context)));
        params.add(new BasicNameValuePair("cargroupid", getCarGroupIdFromLoggedInUser(context)));
        params.add(new BasicNameValuePair("amount", amount));
        params.add(new BasicNameValuePair("mileage", mileage));
        
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(phpUrl, params);
        // return json
        return json;
    }
    
    /**
     * @function get user statistics for selected refuel
     * @param refuelId
     * */
    public JSONObject getUserStatistics(String refuelId){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", cargroup_userstatistics_tag));
        params.add(new BasicNameValuePair("refuelid", refuelId));
        
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(phpUrl, params);
        // return json
        return json;
    }
    
    /**
     * @function get refuelings for cargroup
     * @param context
     * */
    public JSONObject getRefuelings(Context context){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", cargroup_refuelings_tag));
        params.add(new BasicNameValuePair("cargroupid", getCarGroupIdFromLoggedInUser(context)));
        
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
     * @function get email from current logged-in user
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
     * @function get name from current logged-in user
     * */
    public String getNameFromLoggedInUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        int count = db.getRowCount();
        if(count > 0){
            return db.getUserDetails().get("name");
        }
        return "";
    }
    
    /**
     * @function get cargroupid from current logged-in user
     * */
    public String getCarGroupIdFromLoggedInUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        int count = db.getRowCount();
        if(count > 0){
            return db.getUserDetails().get("cargroupid");
        }
        return "";
    }
 
    /**
     * Function to logout user
     * Reset Database
     * */
    public boolean logoutUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        db.deleteUser();
        return true;
    }
 
}