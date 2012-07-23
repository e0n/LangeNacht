package gogo.gadgeto.car;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FuelAdapter  extends ArrayAdapter<FuelEntry>{
	private Context context;
	private int layoutResourceId;
	private FuelEntry data[] = null;
	
	public FuelAdapter(Context context, int layoutResourceId, FuelEntry[] data) {
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
			holder.dateTextView = (TextView) row.findViewById(R.id.fuelListItemDateTextView);
			holder.distanceTextView = (TextView) row.findViewById(R.id.fuelListItemDistanceTextView);
			holder.costTextView = (TextView) row.findViewById(R.id.fuelListItemCostsTextView);
			
			row.setTag(holder);
		}
		else {
			holder = (CashHolder) row.getTag();
		}
		
		// fill holder with data
		FuelEntry fuelEntry = data[position];
		holder.dateTextView.setText(fuelEntry.date);
		holder.distanceTextView.setText(fuelEntry.distance);
		holder.costTextView.setText(parseCentToEuroString(fuelEntry.costs));
		
		return row;
	}
	
	// returns item at position
	@Override
	public FuelEntry getItem(int position) {
		return super.getItem(position);
	}
	
	//changes cent to euro and adds € symbol
	private String parseCentToEuroString(String centString) {
		int length = centString.length();
		String workString = centString;
		while (length < 3) {
			workString = "0" + workString;
			length++;
		}
		String resultString = workString.substring(0, length-2) + "," + workString.substring(length-2) + " €";
		return resultString;
	}
	
	//helperclass
	static class CashHolder
    {
		TextView dateTextView;
        TextView distanceTextView;
        TextView costTextView;
    }

}
