import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {

    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate);

    default double getAverageUsersAge(){
        return getAllUsers().stream()
                .mapToLong(value -> ChronoUnit.YEARS.between(value.getBirthday(), LocalDate.now()))
                .average().orElse(0);
    }

    default boolean isPayableUser(User user){
        return ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()) >= 18;
    }
}
