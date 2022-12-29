package zw.dreamhub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.dreamhub.domain.dto.payload.ProductPayload;
import zw.dreamhub.domain.dto.response.ApiResponse;
import zw.dreamhub.domain.models.Product;
import zw.dreamhub.services.ProductService;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 28/12/2022
 */

@RestController
@RequestMapping(path = "${app.url.unSecured}/products")
@Produces(MediaType.APPLICATION_JSON_VALUE)
@Consumes(MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/search")
    ResponseEntity<ApiResponse> filterProducts(@RequestParam(name = "name") String name) {
        return service.filterProducts(name);
    }

    @PostMapping
    ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody ProductPayload payload) {
        return service.createProduct(payload);
    }

    @PutMapping
    ResponseEntity<ApiResponse> updateProduct(
            @RequestParam(name = "name") String name,
            @Valid @RequestBody ProductPayload payload) {
        return service.updateProduct(name, payload);
    }

    @DeleteMapping
    ResponseEntity<ApiResponse> deleteProduct(
            @RequestParam(name = "name") String name) {
        return service.deleteProduct(name);
    }

}
