package gogo.gadgeto.car;

import android.app.Activity;
import android.content.Context;
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
			holder.txtTitle1 = (TextView) row.findViewById(R.id.cashTxtTitle1);
			holder.txtTitle2 = (TextView) row.findViewById(R.id.cashTxtTitle2);
			
			row.setTag(holder);
		}
		else {
			holder = (CashHolder) row.getTag();
		}
		
		CashEntry cashEntry = data[position];
		holder.txtTitle1.setText(cashEntry.name);
		holder.txtTitle2.setText(cashEntry.dept);
		
		return row;
	}
	
	static class CashHolder
    {
		TextView txtTitle1;
        TextView txtTitle2;
    }
}
