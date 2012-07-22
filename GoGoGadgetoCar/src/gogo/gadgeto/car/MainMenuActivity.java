package gogo.gadgeto.car;

import gogo.gadgeto.car.helper.UserFunctions;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

public class MainMenuActivity extends Activity {
	public static Context appContext;
	
	private int[] tabPositions;
	final int DISTANCE = 0;
	final int FUEL = 1;
	final int STATISTIC = 2;
	final int CARGROUP = 3;
	final int LOGOUT = 4;
	
	ActionBar.Tab distanceTab;
	ActionBar.Tab fuelTab;
	ActionBar.Tab statisticTab;
	ActionBar.Tab carGroupTab;
	ActionBar.Tab logoutTab;
	
	static Fragment fragmentCarGroup;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
        ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        distanceTab 	= bar.newTab().setText("Distance");
        fuelTab 		= bar.newTab().setText("Fuel");
        statisticTab 	= bar.newTab().setText("Statistic");
        carGroupTab   	= bar.newTab().setText("CarGroup");
        logoutTab 		= bar.newTab().setText("Logout");
        
        Fragment fragmentDistance 	= new NewTripFragment();
        Fragment fragmentFuel 		= new FuelFragment();
        Fragment fragmentStatistic	= new StatisticFragment();
        fragmentCarGroup   = new CarGroupFragment();
        Fragment fragmentLogout		= new LogoutFragment();
        
        distanceTab.setTabListener(new MyTabsListener(fragmentDistance));
        fuelTab.setTabListener(new MyTabsListener(fragmentFuel));
        statisticTab.setTabListener(new MyTabsListener(fragmentStatistic));
        carGroupTab.setTabListener(new MyTabsListener(fragmentCarGroup));
        logoutTab.setTabListener(new MyTabsListener(fragmentLogout));
        
        bar.addTab(distanceTab);
        bar.addTab(fuelTab);
        bar.addTab(statisticTab);
        bar.addTab(carGroupTab);
        bar.addTab(logoutTab);
        
        tabPositions = new int[bar.getTabCount()];
        tabPositions[DISTANCE] = distanceTab.getPosition();
        tabPositions[FUEL] = fuelTab.getPosition();
        tabPositions[STATISTIC] = statisticTab.getPosition();
        tabPositions[CARGROUP] = carGroupTab.getPosition();
        tabPositions[LOGOUT] = logoutTab.getPosition();
               
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	ActionBar bar = getActionBar();
    	
    	if (new UserFunctions().getCarGroupIdFromLoggedInUser(getApplicationContext()).equals("null")) {
    		if (distanceTab.getPosition() != -1) 
	    		bar.removeTab(distanceTab);
    		if (fuelTab.getPosition() != -1)
	    		bar.removeTab(fuelTab);
    		if (statisticTab.getPosition() != -1)
	    		bar.removeTab(statisticTab);
    	} else if (bar.getTabCount() < 5){
    		if (distanceTab.getPosition() == -1) 
	    		bar.addTab(distanceTab,0);
    		if (fuelTab.getPosition() == -1)
	    		bar.addTab(fuelTab,0);
    		if (statisticTab.getPosition() == -1)
	    		bar.addTab(statisticTab,0);
//    		bar.addTab(distanceTab,0);
//    		bar.addTab(fuelTab,0);
//    		bar.addTab(statisticTab,0);    		
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }    
}
