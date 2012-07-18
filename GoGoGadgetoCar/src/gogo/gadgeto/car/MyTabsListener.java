package gogo.gadgeto.car;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentTransaction;

public class MyTabsListener implements TabListener{
	
	private Fragment fragment;
	
	public MyTabsListener(Fragment fragment) {
		this.fragment = fragment;
	}
	

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		//Toast.makeText(MainMenuActivity.appContext, "Reselected!", Toast.LENGTH_LONG).show();
		
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(R.id.fragment_container, fragment);
		
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(fragment);		
	}

}
