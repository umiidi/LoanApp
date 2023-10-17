package az.growlab.LoanApp.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class LoanInformation {

    private int id;
    private List<Product> products;
    private BigDecimal totalAmount;
    private BigDecimal preAmount;
    private Double interestRate;

    public BigDecimal getPreAmount() {
        BigDecimal result = BigDecimal.valueOf(0);

        for (Product product : this.getProducts()) {
            result = result.add(product.getPrice());
        }
        return result;
    }

    public BigDecimal getTotalAmount() {
        preAmount = getPreAmount();
        return preAmount.add(preAmount.multiply(BigDecimal.valueOf(interestRate / 100)));
    }
}
