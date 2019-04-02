package cz.vse.ordermanager.order;

import cz.vse.ordermanager.customer.Customer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderState state = OrderState.CREATED;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @RestResource(exported = false)
    @Cascade(CascadeType.ALL)
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @RestResource(exported = false)
    @Cascade(CascadeType.ALL)
    private List<OrderItem> items;

    @PrePersist
    @PreUpdate
    public void updateItemsAssociation() {
        Assert.notEmpty(items, "Cannot create order without items!");
        items.forEach(i -> i.setOrder(this));
    }

    public void setState(OrderState newState) {
        state.validateTransition(newState);
        state = newState;
    }
}
