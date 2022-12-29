package zw.dreamhub.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.dreamhub.domain.enums.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

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
public class Product extends BaseEntity {
    @Column(unique = true)
    private String name = "Ball Point";
    private BigDecimal price = BigDecimal.valueOf(3);
    private Currency currency = Currency.USD;
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
