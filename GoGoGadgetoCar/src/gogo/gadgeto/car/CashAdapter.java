package gogo.gadgeto.car;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CashAdapter extends ArrayAdapter<CashEntry> {

	private Context context;
	private int layoutResourceId;
	private CashEntry data[] = null;
	
	public CashAdapter(Context context, int layoutResourceId, CashEntry[] data) {
		super(context, layoutResourceId, data);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.data = data;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		CashHolder holder = null;
		
		if (row == null) {
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			
			// connect Textviews to holder
			holder = new CashHolder();
			holder.nameTextView = (TextView) row.findViewById(R.id.cashListItemNameTextView);
			holder.deptTextView = (TextView) row.findViewById(R.id.cashListItemDeptTextView);
			holder.mileageTextView = (TextView) row.findViewById(R.id.cashListItemMileageTextView);
			
			row.setTag(holder);
		}
		else {
			holder = (CashHolder) row.getTag();
		}
		// fill holder with data
		CashEntry cashEntry = data[position];
		holder.nameTextView.setText(cashEntry.name);
		holder.deptTextView.setText(parseCentToEuroString(cashEntry.dept));
		holder.deptTextView.setTextColor(Color.GREEN);
		holder.mileageTextView.setText(cashEntry.mileage + " km");
		
		return row;
	}
	
	//changes cent to euro and adds € symbol
	private String parseCentToEuroString(String centString) {
		int length = centString.length();
		String workString = centString;
		//when smaller than 3 chars, fill with 0
		while (length < 3) {
			workString = "0" + workString;
			length++;
		}
		String resultString = workString.substring(0, length-2) + "," + workString.substring(length-2) + " €";
		return resultString;
	}
	
	// helperclass
	static class CashHolder
    {
		TextView nameTextView;
        TextView deptTextView;
        TextView mileageTextView;
    }
}
