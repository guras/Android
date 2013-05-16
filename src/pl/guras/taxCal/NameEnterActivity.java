package pl.guras.taxCal;

import pl.guras.taxCal.dto.Amount;
import pl.guras.taxCal.tools.AmountsManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class NameEnterActivity extends Activity {

	private AmountsManager am;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		am = new AmountsManager(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_name_enter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.name_enter, menu);
		return true;
	}

	public void goToList(View v) {
		Intent previousIntent = getIntent();
		Amount amount = (Amount) previousIntent.getSerializableExtra(MainActivity.AMOUNT);
		EditText et = (EditText) findViewById(R.id.enterName);

		amount.setName(et.getText().toString());
		am.save(amount);
		Intent intent = new Intent(this, ListActivity.class);

		startActivity(intent);
	}
}
