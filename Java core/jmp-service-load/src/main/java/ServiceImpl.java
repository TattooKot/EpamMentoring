import com.google.auto.service.AutoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AutoService(Service.class)
public class ServiceImpl implements Service{

    @Override
    public void subscribe(BankCard bankCard) {
        DB.subscriptions.add(new Subscription(bankCard.getNumber(), LocalDate.now()));
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return DB.subscriptions
                .stream()
                .filter(subscription -> subscription.getBankcard().equals(cardNumber))
                .findAny();
    }

    @Override
    public List<User> getAllUsers() {
        return DB.users;
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
        return DB.subscriptions
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
