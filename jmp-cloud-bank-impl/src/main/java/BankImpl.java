import java.util.Random;

public class BankImpl implements Bank{

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
        char[] digits = new char[12];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < 12; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return new String(digits);
    }
}
