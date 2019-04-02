package cz.vse.ordermanager.order;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    @RestResource(path = "byCustomerId", rel = "byCustomerId")
    Order findByCustomerId(Long id);

    @RestResource(path = "byCustomerName", rel = "byCustomerName")
    List<Order> findByCustomerName(String name);

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(Order entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Order> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
