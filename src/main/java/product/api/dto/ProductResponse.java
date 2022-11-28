package product.api.dto;

import lombok.*;
import org.springframework.beans.BeanUtils;
import product.api.model.Product;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProductResponse of(Product product) {
        var response = new ProductResponse();
        BeanUtils.copyProperties(product, response);

        return response;
    }
}
