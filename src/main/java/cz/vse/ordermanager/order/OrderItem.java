package cz.vse.ordermanager.order;

import cz.vse.ordermanager.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    public BigDecimal getUnitPrice() {
        return product.getPrice();
    }

    public BigDecimal getTotalPrice() {
        return new BigDecimal(quantity).multiply(getUnitPrice());
    }

    public String getProductName() {
        return product.getName();
    }
}
