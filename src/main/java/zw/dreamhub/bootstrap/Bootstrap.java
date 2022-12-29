package zw.dreamhub.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import zw.dreamhub.domain.models.Product;
import zw.dreamhub.domain.repositories.ProductRepository;

import java.util.Optional;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 28/12/2022
 */

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private final ProductRepository repository;

    @Override
    public void run(String... args) {

        // check if products are available
        Optional<Product> price = repository.findFirstByOrderByDateCreatedAsc();

        // product is not available
        if (price.isEmpty()) {
            // persist default product (ball point)
            repository.save(new Product());
        }

    }
}
