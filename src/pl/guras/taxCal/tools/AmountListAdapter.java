package pl.guras.taxCal.tools;

import pl.guras.taxCal.R;
import pl.guras.taxCal.dto.Amount;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AmountListAdapter extends ArrayAdapter<Amount> {

	private Context context;
	private Amount[] values;

	public AmountListAdapter(Context context, Amount[] values) {
		super(context, R.layout.amount_list_entry, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.amount_list_entry, parent, false);
		TextView itemName = (TextView) rowView.findViewById(R.id.itemName);
		TextView fullAmount = (TextView) rowView.findViewById(R.id.fullAmount);
		TextView additionalAmount = (TextView) rowView.findViewById(R.id.amountWithoutVatList);
		TextView countedAmount = (TextView) rowView.findViewById(R.id.finalAmount);

		itemName.setText(values[position].getName());
		fullAmount.setText(Currency.formatAmount(values[position].getAmount()));
		additionalAmount.setText(Currency.formatAmount(values[position].withoutVat()));
		countedAmount.setText(Currency.formatAmount(values[position].withoutIncomeTax()));

		return rowView;
	}
}
