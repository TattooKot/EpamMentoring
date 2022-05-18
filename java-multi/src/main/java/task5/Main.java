package task5;

import task5.model.Currencies;
import task5.model.Transaction;
import task5.model.TransactionFabric;
import task5.model.UserAccount;

import java.io.IOException;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws IOException {
        UserAccount userAccount = new UserAccount("Stas Kot", BigDecimal.valueOf(10000.00));
        TransactionFabric.createTransaction(userAccount, Currencies.UAH, Currencies.USD, 100.00);
    }
}
