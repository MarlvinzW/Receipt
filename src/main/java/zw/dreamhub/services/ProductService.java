package zw.dreamhub.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.dreamhub.domain.dto.payload.ProductPayload;
import zw.dreamhub.domain.dto.response.ApiResponse;
import zw.dreamhub.domain.models.Product;

import java.util.List;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 28/12/2022
 */

@Service
public interface ProductService {

    List<Product> getAllProducts();

    ResponseEntity<ApiResponse> filterProducts(String name);

    ResponseEntity<ApiResponse> createProduct(ProductPayload payload);

    ResponseEntity<ApiResponse> updateProduct(String name, ProductPayload payload);

    ResponseEntity<ApiResponse> deleteProduct(String name);

}
