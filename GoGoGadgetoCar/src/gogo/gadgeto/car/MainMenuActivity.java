package gogo.gadgeto.car;

import gogo.gadgeto.model.Database;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

public class MainMenuActivity extends Activity {
	public static Context appContext;
	
	private Database database;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
        ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        ActionBar.Tab distanceTab 	= bar.newTab().setText("Distance");
        ActionBar.Tab fuelTab 		= bar.newTab().setText("Fuel");
        //ActionBar.Tab settingsTab 	= bar.newTab().setText("Settings");
        
        Fragment fragmentDistance 	= new NewDistanceFragment();
        Fragment fragmentFuel 		= new FuelFragment();
        //Fragment fragmentSettings 	= new SettingsFragment();
        
        distanceTab.setTabListener(new MyTabsListener(fragmentDistance));
        fuelTab.setTabListener(new MyTabsListener(fragmentFuel));
        //settingsTab.setTabListener(new MyTabsListener(fragmentSettings));
        
        bar.addTab(distanceTab);
        bar.addTab(fuelTab);
<<<<<<< HEAD
        //bar.addTab(settingsTab);
=======
        bar.addTab(carGroupTab);
>>>>>>> cb051ab4e70e8137327e01db1b8597d2f9a56877
               
        database = Database.getInstance();
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    public Database getDatabase() {
    	return database;
    }
    
}
