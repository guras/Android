package pl.guras.taxCal;

import pl.guras.taxCal.dto.Amount;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class TabLayoutActivity extends TabActivity {

	public static final String CALC_TAB = "KALKULATOR";
	public static final String LIST_TAB = "LISTA";
	public static final String SUMM_TAB = "PODSUMOWANIE";

	public static final String TARGET_TAB = "targetTab";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);

		Resources res = getResources();
		TabHost tabHost = getTabHost();

		TabSpec inputTab = tabHost.newTabSpec("InputAmount");
		inputTab.setIndicator("KALKULATOR");
		Intent inputIntent = new Intent(this, MainActivity.class);
		inputTab.setContent(inputIntent);

		TabSpec listTab = tabHost.newTabSpec("AmountsList");
		listTab.setIndicator("LISTA");
		Intent listIntent = new Intent(this, ListActivity.class);
		listTab.setContent(listIntent);

		tabHost.addTab(inputTab);
		tabHost.addTab(listTab);

		// On list item edit
		EditText input = (EditText) findViewById(R.id.inputAmount);
		Intent editIntent = getIntent();
		if (null != editIntent) {
			String selectedTab = editIntent.getStringExtra(TARGET_TAB);
			if (null != selectedTab) {
				tabHost.setCurrentTab(selectedTab);
			}
			Amount editedAmount = (Amount) editIntent.getSerializableExtra(MainActivity.AMOUNT);
			if (null != editedAmount) {
				input.setText(editedAmount.getAmount().toString());
			}
		}

	}
}
