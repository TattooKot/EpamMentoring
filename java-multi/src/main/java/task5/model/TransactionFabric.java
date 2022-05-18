package task5.model;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionFabric {
    static ReentrantLock reentrantLock = new ReentrantLock();

    public static Transaction createTransaction(UserAccount userAccount, Currencies currenciesFrom, Currencies currenciesTo, Double amount) {
        ExchangeRates rate = getRate(currenciesFrom, currenciesTo)
                .orElseThrow(NoSuchElementException::new);
        double sum = amount * rate.rate;

        if (userAccount.getUAHBalance().doubleValue() < sum) {
            throw new ArithmeticException("You have not money :(");
        }

        userAccount.setUAHBalance(userAccount.getUAHBalance().min(BigDecimal.valueOf(sum)));
        userAccount.setUSDBalance(userAccount.getUSDBalance().add(BigDecimal.valueOf(amount)));

        Transaction transaction = new Transaction();
        transaction.setUserName(userAccount.getFullName());
        transaction.setCurrenciesFrom(currenciesFrom);
        transaction.setCurrenciesTo(currenciesTo);
        transaction.setAmount(amount);
        transaction.setSum(sum);
        transaction.setLocalDate(LocalDate.now());

        if (!writeTransactionToHistory(transaction, userAccount.getFilePath())) {
            return null;
        }

        System.out.println("Transaction created!");
        System.out.printf("User balance: UAH = %.2f, USD = %.2f", userAccount.getUAHBalance().doubleValue(), userAccount.getUSDBalance().doubleValue());
        return transaction;
    }


    private static Optional<ExchangeRates> getRate(Currencies currenciesFrom, Currencies currenciesTo) {
        String name = currenciesFrom.name() + "_" + currenciesTo.name();
        for (ExchangeRates e : ExchangeRates.values()) {
            if (e.name().equals(name)) {
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }

    private static boolean writeTransactionToHistory(Transaction transaction, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            if (reentrantLock.tryLock(10, TimeUnit.SECONDS)){
                writer.write(transaction.toString() + "\n");
                return true;
            } else return false;
        } catch (Exception i) {
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }
}