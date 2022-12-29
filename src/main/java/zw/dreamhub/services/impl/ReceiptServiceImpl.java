package zw.dreamhub.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.dreamhub.domain.dto.payload.ReceiptItemPayload;
import zw.dreamhub.domain.dto.payload.ReceiptPayload;
import zw.dreamhub.domain.dto.response.ApiResponse;
import zw.dreamhub.domain.dto.response.FailedReceiptResponse;
import zw.dreamhub.domain.enums.ExportType;
import zw.dreamhub.domain.enums.ResponseStatus;
import zw.dreamhub.domain.models.Product;
import zw.dreamhub.domain.models.Receipt;
import zw.dreamhub.domain.models.ReceiptItem;
import zw.dreamhub.domain.repositories.ProductRepository;
import zw.dreamhub.domain.repositories.ReceiptRepository;
import zw.dreamhub.services.ReceiptService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 29/12/2022
 */

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ProductRepository productRepository;

    @Override
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    @Override
    public Set<Receipt> filterReceipts(LocalDate from, LocalDate to) {
        return receiptRepository.findByDateCreatedBetween(from.atStartOfDay(), to.atStartOfDay());
    }

    @Override
    public ResponseEntity<ApiResponse> createReceipt(ReceiptPayload payload) {
        Set<Product> products = productRepository
                .findByNameInIgnoreCase(
                        payload.getItems().stream().map(ReceiptItemPayload::getName)
                                .collect(Collectors.toList())
                );
        if (products.size() != payload.getItems().size()) {
            return ResponseEntity.badRequest().body(new FailedReceiptResponse(products, payload));
        } else {
            Receipt receipt = new Receipt(payload);

            AtomicReference<BigDecimal> total = new AtomicReference<>(BigDecimal.valueOf(0));
            Set<ReceiptItem> receiptItems = new HashSet<>(Collections.emptySet());

            payload.getItems().forEach(item -> {
                Product product = products.stream().filter(filteredItem -> filteredItem.getName().equals(item.getName())).findFirst().get();
                ReceiptItem receiptItem = new ReceiptItem(item.getQuantity(), product);
                total.updateAndGet(v -> BigDecimal.valueOf(v.doubleValue() + (item.getQuantity() * product.getPrice().doubleValue())));
                receiptItems.add(receiptItem);
            });

            receipt.setTotal(total.get());
            receipt.setItems(receiptItems);
            receiptRepository.save(receipt);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, ResponseStatus.SUCCESS));
        }

    }

    @Override
    public ResponseEntity<Object> exportReceipts(ExportType type, LocalDate from, LocalDate to) {
        Set<Receipt> receipts = receiptRepository.findByDateCreatedBetween(from.atStartOfDay(), to.atStartOfDay());
        return null;
    }
}
