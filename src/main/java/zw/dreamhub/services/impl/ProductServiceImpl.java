package zw.dreamhub.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.dreamhub.domain.dto.payload.ProductPayload;
import zw.dreamhub.domain.dto.response.ApiResponse;
import zw.dreamhub.domain.enums.ResponseStatus;
import zw.dreamhub.domain.mappers.ProductMapper;
import zw.dreamhub.domain.models.Product;
import zw.dreamhub.domain.repositories.ProductRepository;
import zw.dreamhub.services.ProductService;

import java.util.List;
import java.util.Optional;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 29/12/2022
 */

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    private static final String PRODUCT_NOT_FOUND = "Product not found";

    @Override
    public List<Product> getAllProducts() {
        return repository.findByIsDeletedFalse();
    }

    @Override
    public ResponseEntity<ApiResponse> filterProducts(String name) {
        Optional<Product> product = repository.findByNameIgnoreCase(name);
        return product.map(item -> ResponseEntity.ok().body(new ApiResponse(true, item))).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponse(false, PRODUCT_NOT_FOUND)));
    }

    @Override
    public ResponseEntity<ApiResponse> createProduct(ProductPayload payload) {
        Optional<Product> product = repository.findByNameIgnoreCase(payload.getName());
        return product.map(item -> ResponseEntity
                        .badRequest()
                        .body(new ApiResponse(false, "Product already exists")))
                .orElseGet(() -> {
                    repository.save(mapper.productPayloadToProduct(payload));
                    return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, ResponseStatus.SUCCESS));
                });
    }

    @Override
    public ResponseEntity<ApiResponse> updateProduct(String name, ProductPayload payload) {
        Optional<Product> product = repository.findByNameIgnoreCase(payload.getName());
        return product.map(item -> {
            repository.save(mapper.updateProductFromProductPayload(payload, item));
            return ResponseEntity
                    .ok()
                    .body(new ApiResponse(true, ResponseStatus.SUCCESS));
        }).orElseGet(() -> ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(false, PRODUCT_NOT_FOUND))
        );
    }

    @Override
    public ResponseEntity<ApiResponse> deleteProduct(String name) {
        Optional<Product> product = repository.findByNameIgnoreCase(name);
        return product.map(item -> {
            item.setDeleted(true);
            repository.save(item);
            return ResponseEntity
                    .ok()
                    .body(new ApiResponse(true, ResponseStatus.SUCCESS));
        }).orElseGet(() -> ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(false, PRODUCT_NOT_FOUND))
        );
    }
}
