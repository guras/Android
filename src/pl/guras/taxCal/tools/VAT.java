package pl.guras.taxCal.tools;

import java.math.BigDecimal;

public enum VAT {
	NONE(BigDecimal.ONE, "0%"), NORMAL(new BigDecimal("1.23"), "23%"), BOOKS(new BigDecimal("1.08"), "8%"), FOOD(new BigDecimal("1.05"), "5%");

	private final BigDecimal value;
	private final String label;

	VAT(BigDecimal val, String lab) {
		value = val;
		label = lab;
	}

	public BigDecimal rate() {
		return value;
	}

	public String label() {
		return label;
	}
}