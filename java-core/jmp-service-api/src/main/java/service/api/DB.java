package service.api;

import model.BankCard;
import model.Subscription;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class DB {
    public static List<Subscription> subscriptions = new ArrayList<>();

    public static List<User> users = new ArrayList<>();

    public static List<BankCard> bankCards = new ArrayList<>();
}
