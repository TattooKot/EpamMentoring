package bank.impl;

import bank.inter.Bank;
import model.BankCard;
import model.BankCardType;
import model.User;

import java.util.Random;

public class BankImpl implements Bank {

    @Override
    public BankCard createBankCard(User user, BankCardType type) {

        if (type.equals(BankCardType.CREDIT)){
            System.out.println("Credit card created....");
        } else {
            System.out.println("Debit card created....");
        }

        String newCardNumber = generateCardNumber();
        return new BankCard(newCardNumber, user);
    }

    private String generateCardNumber() {
        Random random = new Random();
        int result = random.ints(1_000_000_000, Integer.MAX_VALUE)
                .findFirst()
                .orElse(0);

        return String.valueOf(result);
    }
}
