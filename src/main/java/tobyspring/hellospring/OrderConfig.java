package tobyspring.hellospring;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import tobyspring.hellospring.data.JdbcOrderRepository;
import tobyspring.hellospring.order.OrderRepository;
import tobyspring.hellospring.order.OrderService;
import tobyspring.hellospring.order.OrderServiceImpl;
import tobyspring.hellospring.order.OrderServiceTransactionProxy;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

	@Bean
	public OrderRepository orderRepository(DataSource dataSource) {
		return new JdbcOrderRepository(dataSource);
	}

	@Bean
	public OrderService orderService(OrderRepository orderRepository, PlatformTransactionManager transactionManager) {
		return new OrderServiceTransactionProxy(new OrderServiceImpl(orderRepository), transactionManager);
	}
}
