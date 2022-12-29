package zw.dreamhub.domain.dto.payload;

import lombok.Data;
import zw.dreamhub.domain.enums.Currency;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link zw.dreamhub.domain.models.Product} entity
 */

@Data
public class ProductPayload {
    @NotBlank
    @NotNull
    private String name;
    @NotNull
    private BigDecimal value;
    private Currency currency;
}