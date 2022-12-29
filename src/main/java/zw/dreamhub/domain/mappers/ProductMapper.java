package zw.dreamhub.domain.mappers;

import org.mapstruct.*;
import zw.dreamhub.domain.dto.payload.ProductPayload;
import zw.dreamhub.domain.models.Product;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 28/12/2022
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {
    Product productPayloadToProduct(ProductPayload productPayload);

    ProductPayload productToProductPayload(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product updateProductFromProductPayload(ProductPayload productPayload, @MappingTarget Product product);
}
