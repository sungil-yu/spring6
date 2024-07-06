package tobyspring.hellospring.api;

import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tobyspring.hellospring.exrate.ExchangeRateData;

public class ErApiExRateExtractor implements ExRateExtractor{

	@Override
	public BigDecimal extract(String res) {
		ObjectMapper objectMapper = new ObjectMapper();
		ExchangeRateData data = null;
		try {
			data = objectMapper.readValue(res, ExchangeRateData.class);
			return data.conversionRates().get("KRW");
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
