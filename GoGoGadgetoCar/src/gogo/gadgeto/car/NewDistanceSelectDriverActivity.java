package gogo.gadgeto.car;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NewDistanceSelectDriverActivity extends ListActivity {

	private String[] colors = {"Red","Blue","Green","Black","White"};

	  /** Called when the activity is first created. */
	     @Override
	     public void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);
	       
	         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,colors);
	         // Get the activity's ListView and set its choice mode as multiple choice
	         getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	         setListAdapter(adapter);
	     }

	  protected void onListItemClick(ListView l, View v, int position, long id) {
	   // TODO Auto-generated method stub
	   super.onListItemClick(l, v, position, id);

	   Toast.makeText(this,"Item Clicked", Toast.LENGTH_SHORT).show();
	  
	  }
	
}
