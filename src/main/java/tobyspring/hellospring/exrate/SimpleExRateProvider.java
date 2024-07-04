package tobyspring.hellospring.exrate;

import java.math.BigDecimal;

import tobyspring.hellospring.payment.ExRateProvider;

public class SimpleExRateProvider implements ExRateProvider {
	public BigDecimal getExRate(String currency) {
		if (currency.equals("USD")) {
			return new BigDecimal("1100");
		} else {
			return new BigDecimal("1");
		}
	}
}
