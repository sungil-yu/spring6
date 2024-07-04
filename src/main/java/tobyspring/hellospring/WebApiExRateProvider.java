package tobyspring.hellospring;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class WebApiExRateProvider implements ExRateProvider{

    public BigDecimal getExRate(String currency) throws IOException {
        String apiKey = "f05a147b2d7951e5be05b02a";
        String apiUrl = "https://v6.exchangerate-api.com/v6/";

        URL url = new URL(apiUrl + apiKey + "/latest/" + currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String res = br.lines().collect(Collectors.joining());
        br.close();

        ObjectMapper objectMapper = new ObjectMapper();
        ExchangeRateData data = objectMapper.readValue(res, ExchangeRateData.class);
        BigDecimal exRate = data.conversionRates().get("KRW");

        System.out.println("call web api ExRate");
        return exRate;
    }
}

