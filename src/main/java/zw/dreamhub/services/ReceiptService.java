package zw.dreamhub.services;

import org.springframework.http.ResponseEntity;
import zw.dreamhub.domain.dto.payload.ReceiptPayload;
import zw.dreamhub.domain.dto.response.ApiResponse;
import zw.dreamhub.domain.enums.ExportType;
import zw.dreamhub.domain.models.Receipt;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 29/12/2022
 */

public interface ReceiptService {

    List<Receipt> getAllReceipts();

    Set<Receipt> filterReceipts(LocalDate from, LocalDate to);

    ResponseEntity<ApiResponse> createReceipt(ReceiptPayload payload);

    ResponseEntity<Object> exportReceipts(ExportType type, LocalDate from, LocalDate to);

}
