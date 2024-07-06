package tobyspring.hellospring.api;

import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ExRateExtractor {
	BigDecimal extract(String res);
}
