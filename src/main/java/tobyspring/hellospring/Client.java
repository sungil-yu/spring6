package tobyspring.hellospring;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tobyspring.hellospring.payment.Payment;
import tobyspring.hellospring.payment.PaymentService;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        BeanFactory objectFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = objectFactory.getBean(PaymentService.class);

        Payment payment = paymentService.prepare(1L, "USD", new BigDecimal("453.123"));
        System.out.println(payment);
        TimeUnit.SECONDS.sleep(1);

        Payment payment2 = paymentService.prepare(1L, "USD", new BigDecimal("453.123"));
        System.out.println(payment2);
        TimeUnit.SECONDS.sleep(2);

        Payment payment3 = paymentService.prepare(1L, "USD", new BigDecimal("453.123"));
        System.out.println(payment3);
    }
}

