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
			
			holder = new CashHolder();
			holder.nameTextView = (TextView) row.findViewById(R.id.cashListItemNameTextView);
			holder.deptTextView = (TextView) row.findViewById(R.id.cashListItemDeptTextView);
			
			row.setTag(holder);
		}
		else {
			holder = (CashHolder) row.getTag();
		}
		
		CashEntry cashEntry = data[position];
		holder.nameTextView.setText(cashEntry.name);
		holder.deptTextView.setText(parseCentToEuroString(cashEntry.dept));
		if (Integer.parseInt(cashEntry.dept) < 0)
		{
			holder.deptTextView.setTextColor(Color.RED);
		}
		else {
			holder.deptTextView.setTextColor(Color.GREEN);
		}
		
		return row;
	}
	
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

	static class CashHolder
    {
		TextView nameTextView;
        TextView deptTextView;
    }
}
