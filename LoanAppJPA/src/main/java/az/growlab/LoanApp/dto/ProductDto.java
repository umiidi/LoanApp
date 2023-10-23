package az.growlab.LoanApp.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {

    private String name;
    private BigDecimal price;

}