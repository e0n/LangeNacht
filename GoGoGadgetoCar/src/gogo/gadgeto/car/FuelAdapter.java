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
			
			holder = new CashHolder();
			holder.dateTextView = (TextView) row.findViewById(R.id.fuelListItemDateTextView);
			holder.distanceTextView = (TextView) row.findViewById(R.id.fuelListItemDistanceTextView);
			holder.costTextView = (TextView) row.findViewById(R.id.fuelListItemCostsTextView);
			
			row.setTag(holder);
		}
		else {
			holder = (CashHolder) row.getTag();
		}
		
		FuelEntry fuelEntry = data[position];
		holder.dateTextView.setText(fuelEntry.date);
		holder.distanceTextView.setText(fuelEntry.distance);
		holder.costTextView.setText(fuelEntry.costs);
		
		return row;
	}
	
	static class CashHolder
    {
		TextView dateTextView;
        TextView distanceTextView;
        TextView costTextView;
    }

}
