package pl.guras.taxCal.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import pl.guras.taxCal.dto.Amount;
import android.content.Context;
import android.util.Log;

public class AmountsManager {
	private static final String FILE_NAME = "prices";

	private Context ctx;

	public AmountsManager(Context ctx) {
		// Sprawdzam czy istnieje
		this.ctx = ctx;
		File file = ctx.getFileStreamPath(FILE_NAME);
		if (file.exists()) {
			Log.i("Storage File", "It exists");
		} else {
			Log.i("Storage File", "Not exists. Creating new one");
			try {
				List<Amount> newList = new LinkedList<Amount>();
				FileOutputStream fos = ctx.getApplicationContext().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
				ObjectOutputStream outputStream = new ObjectOutputStream(fos);
				outputStream.writeObject(newList);
				fos.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		Log.i("Storage File", "Try to create new one");

	}

	// TODO: co z wyj¹tkiem
	@SuppressWarnings(value = { "unchecked" })
	public List<Amount> getAmounts() {
		try {
			FileInputStream fis = ctx.getApplicationContext().openFileInput(FILE_NAME);
			ObjectInputStream inputStream = new ObjectInputStream(fis);
			List<Amount> list = (List<Amount>) inputStream.readObject();
			inputStream.close();
			fis.close();
			return list;
		} catch (Exception e) {
			throw new RuntimeException("Oh. Something fucked up while reading", e);
		}
	}

	private void saveAmounts(List<Amount> amounts) {
		try {
			FileOutputStream fos = ctx.getApplicationContext().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
			ObjectOutputStream outputStream = new ObjectOutputStream(fos);
			outputStream.writeObject(amounts);
			outputStream.close();
			fos.close();
		} catch (Exception e) {
			throw new RuntimeException("Oh. Something fucked up while writing", e);
		}

	}

	public void save(Amount amt) {
		List<Amount> ams = getAmounts();
		ams.add(amt);
		saveAmounts(ams);
	}

	public void deleteAll() {
		List<Amount> ams = new LinkedList<Amount>();
		saveAmounts(ams);
	}
}
