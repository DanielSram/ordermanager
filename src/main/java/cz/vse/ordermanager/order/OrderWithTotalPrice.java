package cz.vse.ordermanager.order;

import cz.vse.ordermanager.customer.Customer;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;
import java.util.List;

@Projection(name = "withTotalPrice", types = {Order.class})
public interface OrderWithTotalPrice {

    Customer getCustomer();

    List<OrderItem> getItems();

    default BigDecimal getTotalPrice() {
        return getItems().stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
