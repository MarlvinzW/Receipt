package zw.dreamhub.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.dreamhub.domain.dto.payload.ReceiptPayload;
import zw.dreamhub.domain.enums.Currency;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 28/12/2022
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Receipt extends BaseEntity {

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_surname")
    private String customerSurname;

    @Column(name = "customer_address", columnDefinition = "LONGTEXT")
    private String customerAddress;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private Currency currency = Currency.USD;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    private Set<ReceiptItem> items = new HashSet<>(Collections.emptySet());

    public Receipt(ReceiptPayload payload) {
        this.customerName = payload.getCustomerName();
        this.customerSurname = payload.getCustomerSurname();
        this.customerAddress = payload.getCustomerAddress();
    }
}
