package tobyspring.hellospring.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.*;

import tobyspring.hellospring.api.ApiExecutor;
import tobyspring.hellospring.api.ExRateExtractor;
import tobyspring.hellospring.api.SimpleApiExecutor;
import tobyspring.hellospring.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {
    public BigDecimal getExRate(String currency) {
        String url = "https://v6.exchangerate-api.com/v6/f05a147b2d7951e5be05b02a/latest/" + currency;

        return runApiForExRate(url, new SimpleApiExecutor(), res -> {
            ObjectMapper objectMapper = new ObjectMapper();
            ExchangeRateData data = null;
            try {
                data = objectMapper.readValue(res, ExchangeRateData.class);
                return data.conversionRates().get("KRW");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String res = apiExecutor.execute(uri);

        return exRateExtractor.extract(res);
    }



}

