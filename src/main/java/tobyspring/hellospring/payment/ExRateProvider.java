package tobyspring.hellospring.payment;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface ExRateProvider {
	BigDecimal getExRate(String currency);
}
