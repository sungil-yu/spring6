
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tobyspring.hellospring.OrderConfig;
import tobyspring.hellospring.order.Order;
import tobyspring.hellospring.order.OrderReq;
import tobyspring.hellospring.order.OrderService;

import javax.sql.DataSource;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceTest {

	@Autowired
	OrderService orderService;

	@Autowired
	DataSource dataSource;

	@Test
	void testSaveOrder() {
		Order order = orderService.saveOrder("0001", BigDecimal.ONE);
		assertThat(order.getId()).isGreaterThan(0);
	}

	@Test
	void createOrders() {
		List<OrderReq> orderReqs = List.of(new OrderReq("0200", BigDecimal.ONE), new OrderReq("0300", BigDecimal.TWO));
		List<Order> orders = orderService.createOrders(orderReqs);

		assertThat(orders).hasSize(2);
		orders.forEach(order -> assertThat(order.getId()).isGreaterThan(0));

	}



	@Test
	void createOrdersWithDuplicationColum() {
		List<OrderReq> orderReqs = List.of(new OrderReq("0200", BigDecimal.ONE), new OrderReq("0200", BigDecimal.TWO));

		assertThatThrownBy(() -> orderService.createOrders(orderReqs))
				.isInstanceOf(DataIntegrityViolationException.class);


		JdbcClient jdbcClient = JdbcClient.create(dataSource);

		Long count = jdbcClient.sql("select count(*) from orders where no = '0200' ").query(Long.class).single();
		assertThat(count).isEqualTo(0);
	}
}
