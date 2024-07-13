package tobyspring.hellospring.order;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final PlatformTransactionManager transactionManager;

	public OrderService(OrderRepository orderRepository, PlatformTransactionManager transactionManager) {
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


	public List<Order> createOrders(List<OrderReq> reqs) {
		return new TransactionTemplate(transactionManager).execute(status ->
			 reqs.stream().map(req ->saveOrder(req.no(), req.total())).toList()
		);
	}

}
