package tobyspring.hellospring;

import java.math.BigDecimal;

public class SimpleExRateProvider implements ExRateProvider {
	@Override
	public BigDecimal getExRate(String currency) {
		if (currency.equals("USD")) {
			return new BigDecimal("1100");
		} else {
			return new BigDecimal("1");
		}
	}
}
