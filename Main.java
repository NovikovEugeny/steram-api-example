import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class User {
    int id;
    String name;
    int age;
    String universal;

    public User() {
    }

    public User(int id, String name, int age, String universal) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.universal = universal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUniversal() {
        return universal;
    }

    public void setUniversal(String universal) {
        this.universal = universal;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age + '\'' +
                ", universal=" + universal +
                '}';
    }
}

class UserComparator implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        return Integer.compare(u1.getAge(), u2.getAge());
    }
}

public class Main {
    public static void main(String[] args) {

        List<String> strings = new ArrayList<>();
        strings.add("D");
        strings.add("A");
        strings.add("C");
        strings.add("B");
        strings.add("AAA");
        strings.add("aa");
        strings.add("BBBB");

        strings.forEach(System.out::println);

        List<User> users = new ArrayList<>();
        users.add(new User(1, "Artur", 45, "DC"));
        users.add(new User(2, "Bruce", 22, "DC"));
        users.add(new User(3, "Barry", 33, "DC"));
        users.add(new User(4, "Clark", 55, "DC"));
        users.add(new User(5, "Diana", 60, "DC"));
        users.add(new User(6, "Victor", 25, "DC"));
        users.add(new User(7, "Tony", 55, "Marvel"));
        users.add(new User(8, "Steve", 35, "Marvel"));
        users.add(new User(9, "Bruce", 50, "Marvel"));
        users.add(new User(10, "Thor", 30, "Marvel"));

        users.forEach(System.out::println);

        strings.stream().filter(str -> str.length() > 2).forEach(str -> {
            for (char c : str.toCharArray()) {
                System.out.println(c);
            }
            System.out.println("--------------------");
        });

        users.stream().filter(user -> user.age > 45).forEach(System.out::println);

        users.stream().filter(user -> user.age > 45).forEach(user -> {
            System.out.println(user.name);
        });

        //find by id
        clients.stream().filter(c -> c.getId() == id).findFirst().orElse(null); 
        
        //find by email
        clients.stream().filter(c -> c.getLogin().equals(login)).findFirst().orElse(null);

        //Метод limit(long n) применяется для выборки первых n элементов потоков.
        //Этот метод также возвращает модифицированный поток, в котором не более n элементов.
        System.out.println("\nlimit: ");
        users.stream().limit(3).forEach(System.out::println);

        //преобразование из List в Map
        Map<Integer, String> map = users.stream().collect(Collectors.toMap(k -> k.id, v -> v.name));
        System.out.println(map);

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        //java 8
        System.out.println("Java 8 map foreach");
        map.forEach((k,v) -> System.out.println(k + " - " + v));

        //group by
        Map<String, List<User>> universalUser = users.stream().collect(Collectors.groupingBy(User::getUniversal));

        universalUser.forEach((k,v) -> {
            System.out.println("Universal: " + k);
            v.forEach(System.out::println);
            System.out.println("---------------------------------");
        });

        //группировка по условию
        Map<Boolean, List<User>> usersByCondition = users.stream().collect(Collectors.partitioningBy(user -> user.age > 50 ));
        usersByCondition.forEach((k,v) -> {
            System.out.println("Condition: " + k);
            v.forEach(System.out::println);
            System.out.println("---------------------------------");
        });

        //группировка universal - count
        Map<String, Long> uc = users.stream().collect(Collectors.groupingBy(User::getUniversal, Collectors.counting()));
        uc.forEach((k,v) -> {
            System.out.println("Universal: " + k + " - " + v);
        });

        //user with max age
        User maxUser = users.stream().max(Comparator.comparingInt(User::getAge)).get();
        System.out.println("User with max age: " + maxUser);

        //simple type sort
        System.out.println("simple sorting: ");
        strings.stream().sorted().forEach(System.out::println);

        //object sorting
        users.stream().sorted(new UserComparator()).forEach(System.out::println);

        //паралельные потоки
        System.out.println("paralel: ");
        users.parallelStream().forEach(System.out::println);


    }
}
