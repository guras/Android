package pl.guras.taxCal;

import java.io.IOException;
import java.math.BigDecimal;

import pl.guras.taxCal.dto.Amount;
import pl.guras.taxCal.tools.Currency;
import pl.guras.taxCal.tools.VAT;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity implements TextWatcher {

	public static final String AMOUNT = "AMOUNT";

	private VAT currentVatRate = VAT.NORMAL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		EditText input = (EditText) findViewById(R.id.inputAmount);
		input.addTextChangedListener(this);

	}

	@Override
	public void afterTextChanged(Editable s) {
		TextView vatTV = (TextView) findViewById(R.id.amountWithoutVAT);
		TextView vatRateTV = (TextView) findViewById(R.id.VATRate);
		TextView incomeTV = (TextView) findViewById(R.id.amountWithoutIncomeTax);

		Amount inputAmount = getAmountFromInput();
		if (null != inputAmount) {
			vatTV.setText(Currency.formatAmount(inputAmount.withoutVat()));
			incomeTV.setText(Currency.formatAmount(inputAmount.withoutIncomeTax()));
			vatRateTV.setText(currentVatRate.label());
		} else {
			incomeTV.setText("");
			vatTV.setText("");
		}
	}

	private Amount getAmountFromInput() {
		EditText input = (EditText) findViewById(R.id.inputAmount);
		String inputString = input.getText().toString();
		if (null != inputString && inputString.length() > 0) {
			return new Amount(new BigDecimal(input.getText().toString()), currentVatRate);
		}
		return null;
	}

	public void vatRatioChanged(View view) {
		if (!((RadioButton) view).isChecked()) {
			return;
		}

		switch (view.getId()) {
		case R.id.radio_vat_0:
			currentVatRate = VAT.NONE;
			break;
		case R.id.radio_vat_8:
			currentVatRate = VAT.BOOKS;
			break;
		case R.id.radio_vat_23:
			currentVatRate = VAT.NORMAL;
			break;
		}
		afterTextChanged(null);
	}

	public void savePrice(View v) throws IOException, ClassNotFoundException {
		Amount amountToSave = getAmountFromInput();
		if (null != amountToSave) {
			Intent intent = new Intent(this, NameEnterActivity.class);
			intent.putExtra(AMOUNT, amountToSave);
			startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

}
