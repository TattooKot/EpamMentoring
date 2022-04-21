import lombok.SneakyThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;
import java.util.ServiceLoader;

public class MainService {

    private final ServiceLoader<ServiceImpl> serviceLoader = ServiceLoader.load(ServiceImpl.class);
    private final Service service = serviceLoader.findFirst().orElse(null);
    private final Scanner scanner = new Scanner(System.in);
    private final Bank bank = new BankImpl();

    public void createUser(){
        User user = new User();

        System.out.println("Enter name:");
        user.setName(scanner.nextLine());
        System.out.println("Enter surname:");
        user.setSurname(scanner.nextLine());

        String birthday;
        System.out.println("Enter birthday in **.**.**** format:");
        birthday = scanner.nextLine();

        if(birthday.matches("^\\d\\d\\.\\d\\d\\.\\d\\d\\d\\d$")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            user.setBirthday(LocalDate.parse(birthday, formatter));
            DB.users.add(user);

            System.out.println("""
                    Witch type of card do you prefere?:
                    1. Credit
                    2. Debit""");

            BankCard card;
            int cardTypeInt = scanner.nextInt();

            switch (cardTypeInt) {
                case 1 -> card = bank.createBankCard(user, BankCardType.CREDIT);
                case 2 -> card = bank.createBankCard(user, BankCardType.DEBIT);
                default -> {
                    DB.users.remove(user);
                    System.out.println("Bad card type, try again");
                    return;
                }
            }

            DB.bankCards.add(card);
            System.out.println("New card created: " + card.toString());
            return;
        }

        System.out.println("Bad birthday format, try again");
    }

    public void subscribe(){
        String cardNumber = getCardNumber();

        if(cardNumber.equals("0")){
            System.out.println("Bad card number");
            return;
        }

        BankCard card = DB.bankCards.stream()
                .filter(bankCard -> bankCard.getNumber().equals(cardNumber))
                .findFirst()
                .orElse(null);

        if(Objects.isNull(card)){
            System.out.println("Card not found");
            return;
        }

        service.subscribe(card);
        System.out.println("Subscription - done");
    }

    @SneakyThrows
    public void getSubscriptionByCardNumber(){
        String cardNumber = getCardNumber();

        if(cardNumber.equals("0")){
            System.out.println("Bad card number, try again");
            return;
        }

        Subscription subscription =
                service.getSubscriptionByBankCardNumber(cardNumber)
                .orElseThrow(SubsException::new);

        System.out.println(subscription.toString());
    }

    public void getAllUsers(){
        System.out.println(service.getAllUsers().toString());
    }

    public void getAverageUsersAge(){
        System.out.println(service.getAverageUsersAge());
    }

    public void isUserPayable(){
        String cardNumber = getCardNumber();

        BankCard card = DB.bankCards.stream()
                .filter(bankCard -> bankCard.getNumber().equals(cardNumber))
                .findFirst()
                .orElse(null);

        if(Objects.isNull(card)){
            System.out.println("User not found");
            return;
        }

        if(service.isPayableUser(card.getUser())){
            System.out.println("User is payable");
        } else {
            System.out.println("User is under 18");
        }
    }

    public String getCardNumber(){
        String cardNumber;
        System.out.println("Enter card number:");
        cardNumber = scanner.nextLine();

        if(!cardNumber.matches("^\\d{10}$")){
            cardNumber = "0";
        }

        return cardNumber;
    }
}
