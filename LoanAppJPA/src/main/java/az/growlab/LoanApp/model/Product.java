package az.growlab.LoanApp.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private LoanInformation loanInformation;

}