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
        ActionBar.Tab carGroupTab 	= bar.newTab().setText("CarGroup");
        
        Fragment fragmentDistance 	= new NewDistanceFragment();
        Fragment fragmentFuel 		= new FuelFragment();
        Fragment fragmentCarGroup 	= new CarGroupFragment();
        
        distanceTab.setTabListener(new MyTabsListener(fragmentDistance));
        fuelTab.setTabListener(new MyTabsListener(fragmentFuel));
        carGroupTab.setTabListener(new MyTabsListener(fragmentCarGroup));
        
        bar.addTab(distanceTab);
        bar.addTab(fuelTab);
        bar.addTab(carGroupTab);
               
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
