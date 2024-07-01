package tobyspring.hellospring;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

//결제 준비 기능
public class PaymentService {

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws
        IOException {
        BigDecimal exRate = getExRate(currency); //원화 활율
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    private BigDecimal getExRate(String currency) throws IOException {
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
        return exRate;
    }

    public static void main(String[] args) throws IOException {

        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(1L, "USD", new BigDecimal("453.123"));
        System.out.println(payment);

    }


}
