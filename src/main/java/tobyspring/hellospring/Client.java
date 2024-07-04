package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

    public static void main(String[] args) throws IOException {
        BeanFactory objectFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = objectFactory.getBean(PaymentService.class);
        Payment payment = paymentService.prepare(1L, "USD", new BigDecimal("453.123"));
<<<<<<< HEAD
        Payment payment2 = paymentService.prepare(1L, "USD", new BigDecimal("453.123"));

=======
        System.out.println(payment);
>>>>>>> a9957a02ffa90b1166fe1218779db711a1a073ea
    }
}


