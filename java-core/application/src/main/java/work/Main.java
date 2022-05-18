package work;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true){
            MainService service = new MainService();
            Scanner mainScanner = new Scanner(System.in);

            System.out.println("""
                    
                    Welcome!
                    1.Create user with card
                    2.Get all users
                    3.Subscribe (card number needed)
                    4.Get subscription (card number needed)
                    5.Get average users age
                    6.Is user payable? (card number needed)""");

            String number = mainScanner.nextLine();

            switch (number) {
                case "1" -> service.createUser();
                case "2" -> service.getAllUsers();
                case "3" -> service.subscribe();
                case "4" -> service.getSubscriptionByCardNumber();
                case "5" -> service.getAverageUsersAge();
                case "6" -> service.isUserPayable();
                default -> System.out.println("Try again");
            }
        }
    }

}
