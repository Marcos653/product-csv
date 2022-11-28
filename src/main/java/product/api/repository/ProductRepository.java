package product.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.api.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
