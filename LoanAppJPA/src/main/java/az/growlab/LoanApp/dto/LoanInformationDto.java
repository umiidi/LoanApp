package az.growlab.LoanApp.dto;

import lombok.Data;

import java.util.List;

@Data
public class LoanInformationDto {

    private List<ProductDto> products;
    private Double interestRate;

}
