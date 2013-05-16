package pl.guras.taxCal.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {
	private static final BigDecimal INCOME_TAX = new BigDecimal("1.18");

	public static BigDecimal getWithoutVat(BigDecimal price, BigDecimal vat) {
		if (null != price) {
			return price.divide(vat, 2, RoundingMode.HALF_UP);
		}
		return BigDecimal.ZERO;
	}

	public static BigDecimal getWithoutIncomeTax(BigDecimal price) {
		return price.divide(INCOME_TAX, 2, RoundingMode.HALF_UP);
	}

}
