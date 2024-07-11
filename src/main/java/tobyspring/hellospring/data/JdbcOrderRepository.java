package tobyspring.hellospring.data;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.JdbcClient;

import jakarta.annotation.PostConstruct;
import tobyspring.hellospring.order.Order;
import tobyspring.hellospring.order.OrderRepository;

public class JdbcOrderRepository implements OrderRepository {

	private final JdbcClient jdbcClient;

	public JdbcOrderRepository(DataSource dataSource) {
		this.jdbcClient = JdbcClient.create(dataSource);
	}

	@PostConstruct
	void init() {
		jdbcClient.sql("""
				create table orders (id bigint not null,no varchar(255),total numeric(38, 2), primary key (id));
				create sequence orders_SEQ start with 1 increment by 50;
		""").update();
	}

	@Override
	public Order save(Order order) {
		Long id = jdbcClient.sql("SELECT NEXT VALUE FOR orders_SEQ").query(Long.class).single();
		order.setId(id);

		jdbcClient.sql("insert into orders (no, total, id) values (? , ?, ?)")
			.params(order.getNo(), order.getTotal(), order.getId())
			.update();

		return null;
	}
}
