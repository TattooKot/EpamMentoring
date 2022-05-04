package bank.inter;

import model.BankCard;
import model.BankCardType;
import model.User;

public interface Bank {
    BankCard createBankCard(User user, BankCardType type);
}
