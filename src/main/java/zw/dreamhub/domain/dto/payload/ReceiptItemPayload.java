package zw.dreamhub.domain.dto.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 29/12/2022
 */

@Data
public class ReceiptItemPayload {
    @NotBlank
    @NotNull
    private String name;
    @NotNull
    private int quantity;
}
