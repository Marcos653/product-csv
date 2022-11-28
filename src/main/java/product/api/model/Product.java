package product.api.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.*;
import product.api.dto.ProductRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private BigDecimal price;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public static Product of(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
