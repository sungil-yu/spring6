package tobyspring.hellospring;

import java.math.BigDecimal;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import tobyspring.hellospring.order.Order;

public class DataClient {

	public static void main(String[] args) {
		BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);

		EntityManager em = beanFactory.getBean(EntityManagerFactory.class).createEntityManager();

		em.getTransaction().begin();

		Order order = new Order("0001", BigDecimal.valueOf(1000));
		em.persist(order);

		System.out.println(order);

		em.getTransaction().commit();

		em.close();

	}
}
