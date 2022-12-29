package zw.dreamhub.domain.dto.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * A DTO for the {@link zw.dreamhub.domain.models.Receipt} entity
 */

@Data
public class ReceiptPayload {
    @NotNull
    @NotBlank
    private String customerName;
    @NotNull
    @NotBlank
    private String customerSurname;
    @NotNull
    @NotBlank
    private String customerAddress;
    @NotBlank
    private List<ReceiptItemPayload> items;
}