package zw.dreamhub.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 29/12/2022
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReceiptItem extends BaseEntity {

    private int quantity;

    @ManyToOne
    private Product product;

}
