package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) throws IOException {

        PaymentService paymentService = new WebApiExRatePaymentService();
        Payment payment = paymentService.prepare(1L, "USD", new BigDecimal("453.123"));
        System.out.println(payment);

    }
}
