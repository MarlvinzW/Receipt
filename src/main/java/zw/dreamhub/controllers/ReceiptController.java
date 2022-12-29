package zw.dreamhub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.dreamhub.domain.dto.payload.ReceiptPayload;
import zw.dreamhub.domain.dto.response.ApiResponse;
import zw.dreamhub.domain.enums.ExportType;
import zw.dreamhub.domain.models.Receipt;
import zw.dreamhub.services.ReceiptService;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 29/12/2022
 */

@RestController
@RequestMapping(path = "${app.url.unSecured}/receipts")
@Consumes(MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService service;

    @GetMapping
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    List<Receipt> getAllReceipts() {
        return service.getAllReceipts();
    }


    @GetMapping("/search")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    Set<Receipt> filterReceipts(
            @RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return service.filterReceipts(from, to);
    }

    @PostMapping
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiResponse> createReceipt(@Valid @RequestBody ReceiptPayload payload) {
        return service.createReceipt(payload);
    }

    @PostMapping("/export")
//    @Produces(MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> exportReceipts(
            @RequestParam(name = "type") ExportType type,
            @RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return service.exportReceipts(type, from, to);
    }

}
