package gogo.gadgeto.car;

import gogo.gadgeto.model.Database;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class CreateCarGroupActivity extends Activity {
	
	private Button createGroupButton;
	private EditText groupNameEditText;
	
	private Database database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_car_group);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_car_group, menu);
        return true;
    }

    
}
