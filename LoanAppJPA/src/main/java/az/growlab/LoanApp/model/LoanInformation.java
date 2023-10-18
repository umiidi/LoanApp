package az.growlab.LoanApp.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "loan_info")
public class LoanInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "loanInformation")
    private List<Product> products;

    private BigDecimal totalAmount;
    private BigDecimal preAmount;
    private Double interestRate;

    @OneToOne(mappedBy = "loanInfo")
    private Client client;

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
