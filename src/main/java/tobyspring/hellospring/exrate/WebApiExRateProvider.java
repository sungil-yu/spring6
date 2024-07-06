package tobyspring.hellospring.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.*;
import java.util.stream.Collectors;

import tobyspring.hellospring.payment.ExRateProvider;

public class WebApiExRateProvider implements ExRateProvider {

    public BigDecimal getExRate(String currency) {
        String url = "https://v6.exchangerate-api.com/v6/f05a147b2d7951e5be05b02a/latest/";

        URI uri;
        String res;
        try {
            uri = new URI(url + currency);
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                res = br.lines().collect(Collectors.joining());
            }
            ObjectMapper objectMapper = new ObjectMapper();
            ExchangeRateData data = objectMapper.readValue(res, ExchangeRateData.class);
            System.out.println("call web api ExRate");

            return data.conversionRates().get("KRW");

        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}

