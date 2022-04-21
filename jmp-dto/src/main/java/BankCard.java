import lombok.Data;

@Data
public class BankCard {

    private String number;
    private User user;

    BankCard(String number, User user){
        this.number = number;
        this.user = user;
    }
}
