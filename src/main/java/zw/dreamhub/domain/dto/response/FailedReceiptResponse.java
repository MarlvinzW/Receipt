package zw.dreamhub.domain.dto.response;

import lombok.*;
import zw.dreamhub.domain.dto.payload.ReceiptPayload;
import zw.dreamhub.domain.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 29/12/2022
 */

@Getter
@Setter
public class FailedReceiptResponse extends ApiResponse {

    private List<String> invalidProducts = new ArrayList<>();

    public FailedReceiptResponse(Set<Product> receiptProducts, ReceiptPayload payload) {
        super(false, "Invalid product(s)");

        // get products not found
        payload.getItems().forEach(product -> {
            Optional<Product> filteredProduct =
                    receiptProducts.stream().filter(item -> item.getId().equals(product.getName())).findFirst();
            // append product id not found to invalidProducts list
            if (filteredProduct.isEmpty()) {
                this.invalidProducts.add(product.getName());
            }
        });

    }

}
