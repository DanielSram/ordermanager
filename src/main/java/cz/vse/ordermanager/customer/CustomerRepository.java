package cz.vse.ordermanager.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(Customer entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Customer> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();

}
