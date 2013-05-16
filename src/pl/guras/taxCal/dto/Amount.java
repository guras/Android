package pl.guras.taxCal.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import pl.guras.taxCal.tools.VAT;

public class Amount implements Serializable {

	private static final long serialVersionUID = -8856002733302760639L;
	private static final BigDecimal INCOME_TAX = new BigDecimal("1.18");
	private BigDecimal amount;
	private VAT vat;
	private String name;

	public Amount(BigDecimal amount, VAT rate) {
		this.amount = amount;
		this.vat = rate;
	}

	public BigDecimal withoutVat() {
		if (null != amount) {
			return amount.divide(vat.rate(), 2, RoundingMode.HALF_UP);
		}
		return BigDecimal.ZERO;
	}

	public BigDecimal withoutIncomeTax() {
		return withoutVat().divide(INCOME_TAX, 2, RoundingMode.HALF_UP);
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public VAT getRate() {
		return vat;
	}

	public void setRate(VAT rate) {
		this.vat = rate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
