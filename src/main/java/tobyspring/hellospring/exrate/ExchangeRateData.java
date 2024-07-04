package tobyspring.hellospring.exrate;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExchangeRateData(String result, @JsonProperty("conversion_rates") Map<String, BigDecimal> conversionRates) {
}
