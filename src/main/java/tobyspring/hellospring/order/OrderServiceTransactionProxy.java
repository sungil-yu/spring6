package tobyspring.hellospring.order;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

public class OrderServiceTransactionProxy implements OrderService{

	private final OrderService target;
	private final PlatformTransactionManager transactionManager;

	public OrderServiceTransactionProxy(OrderService target, PlatformTransactionManager transactionManager) {
		this.target = target;
		this.transactionManager = transactionManager;
	}

	@Override
	public Order saveOrder(String no, BigDecimal total) {
		return new TransactionTemplate(transactionManager).execute(status -> target.saveOrder(no, total));
	}

	@Override
	public List<Order> createOrders(List<OrderReq> reqs) {
		return new TransactionTemplate(transactionManager).execute(status -> target.createOrders(reqs));
	}
}
