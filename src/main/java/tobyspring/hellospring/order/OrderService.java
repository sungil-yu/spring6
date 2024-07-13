package tobyspring.hellospring.order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order saveOrder(String no, BigDecimal total);

    List<Order> createOrders(List<OrderReq> reqs);
}
