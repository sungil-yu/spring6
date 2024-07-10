package tobyspring.hellospring.order;

import java.math.BigDecimal;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final JpaTransactionManager transactionManager;

	public OrderService(OrderRepository orderRepository, JpaTransactionManager transactionManager) {
		this.orderRepository = orderRepository;
		this.transactionManager = transactionManager;
	}

	public Order saveOrder(String no, BigDecimal total) {

		Order order = new Order(no, total);

		return new TransactionTemplate(transactionManager).execute(status -> {
			orderRepository.save(order);
			return order;
		});
	}
}
