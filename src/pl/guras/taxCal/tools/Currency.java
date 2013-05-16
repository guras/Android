package pl.guras.taxCal.tools;

import java.math.BigDecimal;

public class Currency {

	public static final String PLN = "PLN";

	public static String formatAmount(BigDecimal amount) {
		return String.valueOf(amount) + PLN;
	}
}
