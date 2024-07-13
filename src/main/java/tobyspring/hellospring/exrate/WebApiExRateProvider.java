package tobyspring.hellospring.exrate;

import java.math.BigDecimal;
import tobyspring.hellospring.api.ApiTemplate;
import tobyspring.hellospring.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {

    private final ApiTemplate apiTemplate;

	public WebApiExRateProvider(ApiTemplate apiTemplate) {
		this.apiTemplate = apiTemplate;
	}

	public BigDecimal getExRate(String currency) {
        String url = "https://v6.exchangerate-api.com/v6/f05a147b2d7951e5be05b02a/latest/" + currency;

        return apiTemplate.getExRate(url);
    }
}

