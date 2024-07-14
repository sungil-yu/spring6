package tobyspring.hellospring.order;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Order saveOrder(String no, BigDecimal total) {
		Order order = new Order(no, total);
		orderRepository.save(order);
		return order;
	}



	@Override
	public List<Order> createOrders(List<OrderReq> reqs) {
		return reqs.stream().map(req -> saveOrder(req.no(), req.total())).toList();
	}
}
