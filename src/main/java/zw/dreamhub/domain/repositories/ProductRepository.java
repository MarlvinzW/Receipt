package zw.dreamhub.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.dreamhub.domain.models.Product;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Set<Product> findByNameInIgnoreCase(Collection<String> names);

    Optional<Product> findFirstByOrderByDateCreatedAsc();

    List<Product> findByIsDeletedFalse();

    Optional<Product> findByNameIgnoreCase(String name);
}