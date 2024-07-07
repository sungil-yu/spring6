package tobyspring.hellospring.exrate;

import java.math.BigDecimal;

import org.springframework.web.client.RestTemplate;

import tobyspring.hellospring.payment.ExRateProvider;

public class RestTemplateExRateProvider implements ExRateProvider {

	private final RestTemplate restTemplate;

	public RestTemplateExRateProvider(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public BigDecimal getExRate(String currency) {
		String url = "https://v6.exchangerate-api.com/v6/f05a147b2d7951e5be05b02a/latest/" + currency;

		return restTemplate.getForObject(url, ExchangeRateData.class).conversionRates().get("KRW");
	}
}
