package cz.vse.ordermanager.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(Product entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Product> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
