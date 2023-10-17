package az.growlab.LoanApp.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private int id;
    private int loan_info_id;
    private String name;
    private BigDecimal price;

}