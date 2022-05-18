package task5.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Transaction implements Serializable {
    private String userName;
    private Currencies currenciesFrom;
    private Currencies currenciesTo;
    private Double amount;
    private Double sum;
    private LocalDate localDate;

}
