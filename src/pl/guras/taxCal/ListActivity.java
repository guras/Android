package pl.guras.taxCal;

import pl.guras.taxCal.dto.Amount;
import pl.guras.taxCal.tools.AmountListAdapter;
import pl.guras.taxCal.tools.AmountsManager;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListActivity extends Activity {

	AmountsManager am;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		am = new AmountsManager(this);

		AmountListAdapter ala = new AmountListAdapter(this, am.getAmounts().toArray(new Amount[am.getAmounts().size()]));
		ListView listView = (ListView) findViewById(R.id.amountsList);
		listView.setAdapter(ala);
		listView.setOnItemClickListener(amountClickedHandler);
		setupActionBar();

	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	public void clearList(View v) {
		am.deleteAll();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private OnItemClickListener amountClickedHandler = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			Intent intent = new Intent(v.getContext(), MainActivity.class);

			intent.putExtra(MainActivity.AMOUNT, (Amount) parent.getItemAtPosition(position));
			intent.putExtra(TabLayoutActivity.TARGET_TAB, TabLayoutActivity.CALC_TAB);
			startActivity(intent);
		}
	};

}
