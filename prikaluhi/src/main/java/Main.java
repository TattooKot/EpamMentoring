import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Develop> list = Arrays.asList(
                new Develop("Java Dev", 1, 15),
                new Develop("C Dev", 2, 18));

        List<Develop> collect = list.stream()
                .sorted(Comparator.comparing(Develop::getExperience).thenComparing(Develop::getAge))
                .toList();

        List<Develop> collect2 = list.stream()
                .sorted(createComparatorComparing(Develop::getExperience))
                .toList();

        List<Develop> collect3 = list.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .toList();

        Function<Person, Integer> function = Person::getAge;
        Comparator<Develop> comparator = createComparatorComparing(function);

        List<Develop> collect4 = list.stream()
                .sorted(createComparatorComparing(Person::getAge))
                .toList();

        List<Company> companies = new ArrayList<>();

        companies.stream()
                .sorted(createComparatorComparing(Company::getDevelop));
    }

    static <T, R extends Comparable<? super R>> Comparator<T> createComparatorComparing(Function<? super T, ? extends R> function){
        return (obj1, obj2) -> function.apply(obj1).compareTo(function.apply(obj2));
    }

    @Data
    @AllArgsConstructor
    private static class Person{
        private Integer age;
    }

    @Getter
    private static class Develop extends Person implements Comparable<Person>{
        private final String role;
        private final Integer experience;

        public Develop(String role, Integer experience, Integer age) {
            super(age);
            this.role = role;
            this.experience = experience;
        }

        @Override
        public int compareTo(Person o) {
            return 0;
        }
    }

    @Getter
    @AllArgsConstructor
    private static class Company{
        private Develop develop;
    }
}
