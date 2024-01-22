package mobi.foo.assignment.repository;

import mobi.foo.assignment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByCategory(String category);

    Optional<Product> findByName(String name);

    Optional<Product> findByIsAvailable(Boolean isAvailable);
}
