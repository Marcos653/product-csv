package product.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private Long id;
    @NotBlank
    private String name;
    @NotEmpty
    private BigDecimal price;
}
