package tobyspring.hellospring.data;

import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tobyspring.hellospring.order.Order;

public class OrderRepository {

	@PersistenceContext
	private EntityManager em;



	public void save(Order order) {
		em.persist(order);
	}
}
